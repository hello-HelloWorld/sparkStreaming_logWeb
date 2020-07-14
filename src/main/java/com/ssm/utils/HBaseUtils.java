package com.ssm.utils;

/*
 * @author: sunxiaoxiong
 * @date  : Created in 2020/7/13 15:33
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HBaseUtils {
    private Configuration configuration = null;
    private Connection connection = null;
    private static HBaseUtils hBaseUtils = null;

    private HBaseUtils() {
        try {
            configuration = new Configuration();
            //zk服务器的地址
            configuration.set("hbase.zookeeper.quorum", "hadoop102:2181");
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获得hbaseutils的实例
    public static synchronized HBaseUtils getInstance() {
        if (hBaseUtils == null) {
            hBaseUtils = new HBaseUtils();
        }
        return hBaseUtils;
    }

    //根据表名获取表对象
    public HTable getHtable(String tableName) {
        try {
            HTable table = (HTable) connection.getTable(TableName.valueOf(tableName));
            return table;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据日期查询统计结果
    public Map<String, Long> getClickCount(String tableName, String date) {
        Map<String, Long> map = new HashMap<>();
        try {
            //得到表的实例
            HTable htable = getInstance().getHtable(tableName);
            //列族
            String cf = "info";
            //列
            String qualifer = "click_count";
            //定义扫描器前缀过滤器，只扫rowKey前缀带有date的row
            PrefixFilter prefixFilter = new PrefixFilter(Bytes.toBytes(date));
            //定义扫描器
            Scan scan = new Scan();
            scan.setFilter(prefixFilter);
            ResultScanner results = htable.getScanner(scan);
            for (Result result : results) {
                Cell[] cells = result.rawCells();
                /*for (Cell cell:cells){
                    //rowKey
                    String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
                    //点击次数
                    long clickCount = Bytes.toLong(CellUtil.cloneValue(cell));
                    map.put(rowKey, clickCount);
                }*/
                //取出rowKey
                String rowKey = Bytes.toString(result.getRow());
                //取出点击次数
                Long clickCount = Bytes.toLong(result.getValue(cf.getBytes(), Bytes.toBytes(qualifer)));
                map.put(rowKey, clickCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }
}

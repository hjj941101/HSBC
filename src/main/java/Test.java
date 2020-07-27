import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {

    private static Map<String, List> map = initMap();

    public static Map initMap(){
        Map<String, List> map = new HashMap<String, List>();
        map.put("0", Arrays.asList(""));
        map.put("1", Arrays.asList(""));
        map.put("2", Arrays.asList("A","B","C"));
        map.put("3", Arrays.asList("D","E","F"));
        map.put("4", Arrays.asList("G","H","I"));
        map.put("5", Arrays.asList("J","K","L"));
        map.put("6", Arrays.asList("M","N","O"));
        map.put("7", Arrays.asList("P","Q","R","S"));
        map.put("8", Arrays.asList("T","U","V"));
        map.put("9", Arrays.asList("W","X","Y","Z"));
        return map;
    }

    private static List<String[]> sortLetters(List<String[]> dataList, int index, List<String[]> resultList){
        if(index == dataList.size()) {
            return resultList;
        }

        List<String[]> result = new ArrayList<String[]>();
        if(index == 0) {//第一列数组默认有多少个字母就添加多少个排列数据
            String[] dataArr = dataList.get(0);
            for(String s : dataArr) {
                result.add(new String[]{s});
            }
        }else {
            String[] dataArr = dataList.get(index);
            for(String[] dataArr0: resultList) {
                for(String s : dataArr) {
                    //复制数组并扩充新元素
                    String[] dataArrCopy = new String[dataArr0.length+1];
                    System.arraycopy(dataArr0, 0, dataArrCopy, 0, dataArr0.length);
                    dataArrCopy[dataArrCopy.length-1] = s;
                    //追加到结果集
                    result.add(dataArrCopy);
                }
            }
        }
        //递归
        return sortLetters(dataList, ++index, result);
    }

    public static void main(String[] args) {
        System.out.println("Please input number from 0 to 9:");
        //输入按键数字1-9
        Scanner input = new Scanner(System.in);
        String number = "";
        while (true) {
            number = input.next();
            //输入0~9, 最多输入两位数
            if(!number.matches("[0-9]{1,2}")) {
                System.out.println("输入不合法！只能输入0-99的数字,请重新输入：");
            }
            break;
        }

        StringBuilder arrInput = new StringBuilder("Input:arr[] ={");
        String[] arrStr = number.split("");
        List<String[]> dataList = new ArrayList<String[]>();
        for(int i=0; i<arrStr.length; i++) {
            arrInput.append(arrStr[i]);
            if(i<arrStr.length-1) {
                arrInput.append(",");
            }
            //先将多个list中的数据都添加到同一个集合中作为数据源
            List<String> lettersList = map.get(arrStr[i]);
            if(lettersList.size() > 0) {
                String[] letterArr = (String[]) lettersList.toArray();
                dataList.add(letterArr);
            }
        }
        arrInput.append("}");

        //递归实现多数组排列组合，并返回最终的排列集合
        List<String[]> resultList = sortLetters(dataList,0,null);
        //打印输入内容
        System.out.println(arrInput.toString());
        System.out.print("Output:");
        //打印输出排列组合结果

        String[] resultArr;
        for(int i=0; i<resultList.size(); i++) {
            resultArr = resultList.get(i);
            System.out.print(" ");
            for(String arr: resultArr) {
                System.out.print(arr);
            }
        }
    }

}

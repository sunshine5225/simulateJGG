package com.hong.testProblem;

import java.util.*;

public class SimulateJGGForm {
    private static Map<Integer, Set<String>> baseIntMap;
    private Map<Integer, Set<String>> customIntMap;

    static {
        baseIntMap = new HashMap<>(10);

        Set<String> set0 = new HashSet<>(1);
        set0.add("0");
        baseIntMap.put(0, set0);
        Set<String> set1 = new HashSet<>(1);
        set1.add("1");
        baseIntMap.put(1, set1);

        Set<String> set2 = new HashSet<>(3);
        set2.add("a");
        set2.add("b");
        set2.add("c");
        baseIntMap.put(2, set2);

        Set<String> set3 = new HashSet<>(3);
        set3.add("d");
        set3.add("e");
        set3.add("f");
        baseIntMap.put(3, set3);

        Set<String> set4 = new HashSet<>(3);
        set4.add("g");
        set4.add("h");
        set4.add("i");
        baseIntMap.put(4, set4);

        Set<String> set5 = new HashSet<>(3);
        set5.add("j");
        set5.add("k");
        set5.add("l");
        baseIntMap.put(5, set5);

        Set<String> set6 = new HashSet<>(3);
        set6.add("m");
        set6.add("n");
        set6.add("o");
        baseIntMap.put(6, set6);

        Set<String> set7 = new HashSet<>(4);
        set7.add("p");
        set7.add("q");
        set7.add("r");
        set7.add("s");
        baseIntMap.put(7, set7);

        Set<String> set8 = new HashSet<>(3);
        set8.add("t");
        set8.add("u");
        set8.add("v");
        baseIntMap.put(8, set8);

        Set<String> set9 = new HashSet<>(4);
        set9.add("w");
        set9.add("x");
        set9.add("y");
        set9.add("z");
        baseIntMap.put(9, set9);
    }

    public SimulateJGGForm(int num) {
        if (num < 10) {
            customIntMap = new HashMap<>(baseIntMap);
        } else {
            customIntMap = new HashMap<>(num + 1);
            customIntMap.putAll(baseIntMap);
            Set<String> currentSet = null;
            for (int i = 10; i <= num; i++) {
                currentSet = new HashSet<>();
                int j = i;
                while (j > 0) {
                    int ys = j % 10;
                    currentSet.addAll(baseIntMap.get(ys));
                    j = j / 10;
                }
                customIntMap.put(i, currentSet);
            }
        }
    }

    /**
     * 转换单个
     *
     * @param num
     * @return
     */
    public String convertSingleNum(int num) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (String target : customIntMap.get(num)) {
            stringBuilder.append(target);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 转换方法
     *
     * @param orignNum
     * @return
     */
    public String convertListNum(int[] orignNum) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Set<String>> selectConvertList = new ArrayList<>(orignNum.length);
        int totalList = 1;
        for (int i = 0; i < orignNum.length; i++) {
            selectConvertList.add(customIntMap.get(orignNum[i]));
            totalList = totalList * customIntMap.get(orignNum[i]).size();
        }

        List<String> targetList = new ArrayList<>(totalList);
        doLoadFromList(selectConvertList, 0, new StringBuilder(""), targetList);

        for (String target : targetList) {
            stringBuilder.append(target);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * 递归转换
     *
     * @param selectConvertList
     * @param index
     * @param preFix
     * @param targetList
     */
    private void doLoadFromList(List<Set<String>> selectConvertList, int index, StringBuilder preFix, List<String> targetList) {
        if (index == selectConvertList.size() - 1) {
            for (String s : selectConvertList.get(index)) {
                targetList.add(preFix + s);
            }
        } else {
            for (String s : selectConvertList.get(index)) {
                if (index == 0) {
                    preFix = new StringBuilder("");
                }
                preFix.append(s);
                doLoadFromList(selectConvertList, index + 1, preFix, targetList);
                //每次循环执行，回归原来的前缀
                String prefixStr = preFix.toString();
                preFix.deleteCharAt(prefixStr.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("请输入数组，用英式逗号隔开：");
        Scanner sc = new Scanner(System.in);
        String str = sc.next().toString();
        String[] arr = str.split(",");
        int[] b = new int[arr.length];
        for (int j = 0; j < b.length; j++) {
            b[j] = Integer.parseInt(arr[j]);
        }

        SimulateJGGForm simulateJGGForm = new SimulateJGGForm(99);
        System.out.println(simulateJGGForm.convertListNum(b));
    }
}

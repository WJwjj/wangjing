import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.io.IOException;

public class wordcount { 
	static Scanner in = new Scanner(System.in);
	Map<String,Integer> Map = new LinkedHashMap<String, Integer>();
	
	Map read(String A) throws IOException 
	{
	//读取英文文本
		Map<String, Integer> map = new TreeMap<String, Integer>();
	    BufferedReader b = null;
		try {
			b = new BufferedReader(new FileReader(A));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("文件没有找到，请输入正确的文件路径！");
		}
		
 	//定义一个Map类的对象map存放统计的单词
		String value= b.readLine();
         //从b中读取单词（一行一行的读），并将其赋值给value
		while (value!= null) {//统计文本中每个单词出现的次数（while循环）
         	//处理标点符号
			String[] words = value.split("[^a-zA-Z]"); //将单词通过标点符号分割，并将分割好的单词放到名为words的数组中
         	for (int i = 0; i < words.length; i++) {
         		//将大写字母转换为小写字母
         		String key = words[i].toLowerCase();
             		if (key.length() > 0) {
                   		if (!map.containsKey(key)) {  //可以查看map类中的containskey，put，get方法
                       		map.put(key, 1);
                       		} 
                   		else { 
                   			int k = map.get(key)+1;// 如果不是第一次出现，就把k值++
                             map.put(key, k);
                       		}
                   		}
               		} 
             value = b.readLine();
         }
		return map;
	}
	
    //显示词频和柱状图(histogram)
    void histogram(Map<String, Integer> map){
        System.out.println("请输入需要查询的单词 :");
        String words = in.nextLine();
        String[] word= words.split(",");//输入的单词之间以逗号分隔
        float sum;  
        for(int i=0; i<word.length; i++) {
        	for(Map.Entry<String,Integer> y : map.entrySet()) { //可以查看Map类中的entrySet()方法和Entry()方法，for中是一个赋值语句
        		if(word[i].equals(y.getKey()))
        		{  
        			System.out.println("单词 —— "+y.getKey() + " 出现 （" + y.getValue()+" ）次");
        			sum=(float)(y.getValue())/500; 
        			for(int j=0;j<sum;j++){
        				System.out.print("*");
        			}
        			System.out.println();
        		}  
            } 
        }
    }
    
   //输出高频词
    void HighWord(Map<String, Integer> map) {  
    	sort(map,2);//先利用上一个方法sort（）来对统计好的单词按单词出现的频数排序，然后再进行前k个单词的选取
        System.out.println("请输入查看单词个数：");
        int k = in.nextInt();
        for(Entry<String,Integer> y : Map.entrySet()) {  
            System.out.println("单词——"+y.getKey() + " 出现" + y.getValue()+"次");  
            k--;
            if(k<=0)	
            	break;
        } 
    }     
    
  //排序 
    void sort(Map<String, Integer> map,int a) {  
       Set<Entry<String,Integer>> m= map.entrySet();   
       LinkedList<Entry<String, Integer>> List = new LinkedList<Entry<String,Integer>>(m);//定义一个Entry<String, Integer>
       if(a==2) {      //按单词出现的频数从大到小排列                                                                                                                                                                 //类型的linkedList数组，数组名为list
    	   Collections.sort(List, new Comparator<Entry<String,Integer>>() {                   //可以查看LinkedList的用法
    		   public int compare(Entry<String, Integer> a,  Entry<String, Integer> b) {  
    			   return b.getValue().compareTo(a.getValue());  //可以查看compare的用法
    		   }     
    	   });  
       }
       else if(a==3) {//按单词的字典序排列
    	   Collections.sort(List, new Comparator<Entry<String,Integer>>() {     
               public int compare(Entry<String, Integer> a,  Entry<String, Integer> b) {  
                   return a.getKey().compareTo(b.getKey());  
              }     
           });  
       }
       for(Entry<String,Integer> entry: List) {  
           Map.put(entry.getKey(), entry.getValue());  
       }  
   } 
    
    //将结果存入文件中result.txt
    void output(Map<String, Integer> map)throws IOException { 
    	sort(map,3);
        File file = new File("result.txt");//创建一个新的文件
        FileWriter f = new FileWriter(file.getAbsoluteFile());//调用了getAbsoluteFile（）方法
        for(Entry<String,Integer> y: Map.entrySet()) {
        	f.write(y.getKey() + ":" + y.getValue()+"     ");
        }
        f.close();//关闭文件
        System.out.println("成功写入result文件中！");
    }  
}
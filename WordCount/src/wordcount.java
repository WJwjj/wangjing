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
	//��ȡӢ���ı�
		Map<String, Integer> map = new TreeMap<String, Integer>();
	    BufferedReader b = null;
		try {
			b = new BufferedReader(new FileReader(A));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�ļ�û���ҵ�����������ȷ���ļ�·����");
		}
		
 	//����һ��Map��Ķ���map���ͳ�Ƶĵ���
		String value= b.readLine();
         //��b�ж�ȡ���ʣ�һ��һ�еĶ����������丳ֵ��value
		while (value!= null) {//ͳ���ı���ÿ�����ʳ��ֵĴ�����whileѭ����
         	//���������
			String[] words = value.split("[^a-zA-Z]"); //������ͨ�������ŷָ�����ָ�õĵ��ʷŵ���Ϊwords��������
         	for (int i = 0; i < words.length; i++) {
         		//����д��ĸת��ΪСд��ĸ
         		String key = words[i].toLowerCase();
             		if (key.length() > 0) {
                   		if (!map.containsKey(key)) {  //���Բ鿴map���е�containskey��put��get����
                       		map.put(key, 1);
                       		} 
                   		else { 
                   			int k = map.get(key)+1;// ������ǵ�һ�γ��֣��Ͱ�kֵ++
                             map.put(key, k);
                       		}
                   		}
               		} 
             value = b.readLine();
         }
		return map;
	}
	
    //��ʾ��Ƶ����״ͼ(histogram)
    void histogram(Map<String, Integer> map){
        System.out.println("��������Ҫ��ѯ�ĵ��� :");
        String words = in.nextLine();
        String[] word= words.split(",");//����ĵ���֮���Զ��ŷָ�
        float sum;  
        for(int i=0; i<word.length; i++) {
        	for(Map.Entry<String,Integer> y : map.entrySet()) { //���Բ鿴Map���е�entrySet()������Entry()������for����һ����ֵ���
        		if(word[i].equals(y.getKey()))
        		{  
        			System.out.println("���� ���� "+y.getKey() + " ���� ��" + y.getValue()+" ����");
        			sum=(float)(y.getValue())/500; 
        			for(int j=0;j<sum;j++){
        				System.out.print("*");
        			}
        			System.out.println();
        		}  
            } 
        }
    }
    
   //�����Ƶ��
    void HighWord(Map<String, Integer> map) {  
    	sort(map,2);//��������һ������sort��������ͳ�ƺõĵ��ʰ����ʳ��ֵ�Ƶ������Ȼ���ٽ���ǰk�����ʵ�ѡȡ
        System.out.println("������鿴���ʸ�����");
        int k = in.nextInt();
        for(Entry<String,Integer> y : Map.entrySet()) {  
            System.out.println("���ʡ���"+y.getKey() + " ����" + y.getValue()+"��");  
            k--;
            if(k<=0)	
            	break;
        } 
    }     
    
  //���� 
    void sort(Map<String, Integer> map,int a) {  
       Set<Entry<String,Integer>> m= map.entrySet();   
       LinkedList<Entry<String, Integer>> List = new LinkedList<Entry<String,Integer>>(m);//����һ��Entry<String, Integer>
       if(a==2) {      //�����ʳ��ֵ�Ƶ���Ӵ�С����                                                                                                                                                                 //���͵�linkedList���飬������Ϊlist
    	   Collections.sort(List, new Comparator<Entry<String,Integer>>() {                   //���Բ鿴LinkedList���÷�
    		   public int compare(Entry<String, Integer> a,  Entry<String, Integer> b) {  
    			   return b.getValue().compareTo(a.getValue());  //���Բ鿴compare���÷�
    		   }     
    	   });  
       }
       else if(a==3) {//�����ʵ��ֵ�������
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
    
    //����������ļ���result.txt
    void output(Map<String, Integer> map)throws IOException { 
    	sort(map,3);
        File file = new File("result.txt");//����һ���µ��ļ�
        FileWriter f = new FileWriter(file.getAbsoluteFile());//������getAbsoluteFile��������
        for(Entry<String,Integer> y: Map.entrySet()) {
        	f.write(y.getKey() + ":" + y.getValue()+"     ");
        }
        f.close();//�ر��ļ�
        System.out.println("�ɹ�д��result�ļ��У�");
    }  
}
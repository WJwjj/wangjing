import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.IOException;  
import java.util.Map;
import java.util.Scanner; 
import java.util.TreeMap;
import java.io.IOException;
public class Main { 
	static Scanner in = new Scanner(System.in);
    public static void main(String[] args)throws IOException{  
    	Map<String, Integer> map = new TreeMap<String, Integer>();
    	// ��ȡҪ������ļ�������������b��
    	System.out.println("~~~~~~~~��Ƶͳ��С���~~~~~~~~");    	
    	System.out.println("��ѡ�����¹������:");
    	System.out.println("1����ȡ�ı�");
    	System.out.println("2����ʾ��Ҫ��ѯ�ĵ��ʴ�Ƶ");
    	System.out.println("3�������Ƶ��ߵ�ǰk������");
    	System.out.println("4������������result.txt��");
    	System.out.println("0���˳�");
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");  
    	int y= in.nextInt();  
    	int i=0;
    	while(y!=0){ 
    		if(i==0 && y==2 || i==0 && y==3 || i==0 && y==4)
    		{
    			System.out.println("û�������ı����ݣ�");
    			y=1;
    		}
    		switch(y){
    			case 1:{
    				System.out.println("�������ı��洢·����");
                	String src = in.next();
                	wordcount wc = new wordcount();
                	map = wc.read(src);
                	if(map.size()==0)
                	{
                		System.out.println("�ļ����ı�����Ϊ�գ�������ѡ����1�����ļ���ȡ��");
                	}
                	i++;
                	break;
    			}
    			case 2:{
    				wordcount res = new wordcount();
    				res.histogram(map);
    				i++;
    			}
    			break;
    			case 3:{
    				wordcount res = new wordcount();
    				res.HighWord(map);
    				i++;
    			} 
    			break;
    			case 4:{
    				wordcount res = new wordcount();
    				res. output(map);
    				i++;
    			}
    			break;
    		}
    		System.out.println("chose function:");
    		y= in.nextInt();  
    	}
    }    
}
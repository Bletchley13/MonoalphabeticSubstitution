import java.io.*;


public class decry {
	public static void main(String argv[]) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("ciphertext.txt"));
			BufferedReader keyf = new BufferedReader(new FileReader("key.txt"));
			BufferedWriter wf = new BufferedWriter(new FileWriter("plaintext.txt"));
			char rkey[]=new char[26];
			
			String key=keyf.readLine();
			for(char i='a';i<='z';i++)
			{
				rkey[key.charAt(i-'a')-'a']=i;
			}
			System.out.println(rkey);
			StringBuffer strb;
			String str;
			while(((str=bf.readLine()))!= null)
			{
				strb=new StringBuffer(str);
				System.out.println(strb);
				//String ans;
				for(int i=0;i < strb.length();i++)
				{
					if (strb.charAt(i) >= 'a' && strb.charAt(i) <= 'z') {
						strb.setCharAt(i, rkey[strb.charAt(i) - 'a']);
					}
				     
				}
				System.out.println(strb.toString());
				wf.write(strb.toString());
				wf.newLine();
			}
			
			wf.close();
			keyf.close();
			bf.close();
			
			
		} catch (IOException e) {

		}
	}
}

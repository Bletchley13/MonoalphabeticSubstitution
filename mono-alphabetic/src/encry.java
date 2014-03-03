import java.io.*;

public class encry {
	public static void main(String argv[]) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("plaintext.txt"));
			BufferedReader keyf = new BufferedReader(new FileReader("key.txt"));
			BufferedWriter wf = new BufferedWriter(new FileWriter("ciphertext.txt"));

			String key = keyf.readLine();
			System.out.println(key + " " + key.length());
			StringBuffer strb;
			String str;
			while (((str = bf.readLine())) != null) {
				strb = new StringBuffer(str);
				System.out.println(strb);
				// String ans;
				for (int i = 0; i < strb.length(); i++) {
					if (strb.charAt(i) >= 'a' && strb.charAt(i) <= 'z') {
						strb.setCharAt(i, key.charAt(strb.charAt(i) - 'a'));
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

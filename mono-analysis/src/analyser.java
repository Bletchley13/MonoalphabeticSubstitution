import java.util.*;
import java.io.*;
import java.util.Arrays;

class cmp implements Comparator {
	public int compare(Object obj1, Object obj2) {
		a o1 = (a) obj1;
		a o2 = (a) obj2;
		return (o1.count >= o2.count) ? 1 : 0;
	}
}

class a {
	int count;
	char ch;
};

public class analyser {
	static String frequen = "etiaonslcmhpdufygbwvrkxqzj";// 6
	static String key;
	static int limmit = 3;
	static String table[];
	static int index;
	static String text[];
	static int text_index;
	static int rep=1;

	static void frequency() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(
					"ciphertext.txt"));
			a table[] = new a[26];
			for (int i = 0; i < 26; i++) {
				table[i] = new a();
				table[i].count = 0;
				table[i].ch = (char) (i + 'a');
			}
			String str;
			while ((str = bf.readLine()) != null) {
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
						table[str.charAt(i) - 'a'].count++;
				}
			}

			Arrays.sort(table, new cmp());

			char tt[] = new char[26];
			for (int i = 25; i >= 0; i--) {
				System.out.println(table[i].ch + " " + table[i].count);
				tt[(int) (frequen.charAt(25 - i) - 'a')] = table[i].ch;
			}
			System.out.println(tt);
			key = new String(tt);
			System.out.println(key);
			bf.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	public static void main(String argv[]) {

		frequency();
		make_dictionary_table();
		make_text_table();
		match();
		decry();
		// check()
	}

	static void match() {
		char map[] = new char[26];
		Boolean set[] = new Boolean[26];
		for (int i = 0; i < 26; i++)
			set[i] = false;

		for (int i = 0; i < limmit; i++) {
			map[frequen.charAt(i) - 'a'] = key.charAt(frequen.charAt(i) - 'a');
			set[frequen.charAt(i) - 'a'] = true;
			System.out.print(map[frequen.charAt(i) - 'a'] + " ");
		}
        for(int t=0;t<rep;t++){
		for (int i = 0; i < index; i++) {
			int cc = 0;
			String cand = "";
			int already = 0;
			for (int j = 0; j < text_index & already == 0; j++) {
				int diff = 0;
				if (table[i].length() != text[j].length())
					continue;

				for (int k = 0; k < table[i].length(); k++) {
					char tt = table[i].charAt(k);
					char tt2 = text[j].charAt(k);
					if (set[tt - 'a'] && map[tt - 'a'] == tt2)
						continue;
					if (set[tt - 'a'] && map[tt - 'a'] != tt2) {
						diff = -1;
						break;
					}
					if (!set[tt - 'a'])
						diff++;
				}
				if (diff == -1)
					continue;
				if (diff == 0)
					already = 1;

				if (diff >= 1) {
					cc++;
					cand = new String(text[j]);
				}

			}
			if (cc == 1) {
				System.out.println(table[i] + " " + cand);
				for (int k = 0; k < table[i].length(); k++) {
					char tt = table[i].charAt(k);
					char tt2 = cand.charAt(k);
					if (!set[tt - 'a']) {
						set[tt - 'a'] = true;
						map[tt - 'a'] = tt2;
					}
				}
			}
		}
        }
		for (int i = 0; i < 26; i++)
			System.out.println((char) ('a' + i) + " " + set[i] + " " + map[i]);

		key = new String(map);
		System.out.println(key);
	}

	static void make_dictionary_table() {
		try {
			Scanner in = new Scanner(new File("dictionary.txt"));
			table = new String[400];
			index = 0;
			while (in.hasNext()) {
				String str = in.next();
				// System.out.print(str);
				table[index++] = new String(str);
			}

			// showdiction();
			// for(int i=0;i<index;i++)
			// System.out.println(table[i]);
			in.close();
		} catch (FileNotFoundException e) {
		}
	}

	static void make_text_table() {
		try {
			Scanner in = new Scanner(new FileReader("ciphertext.txt"));
			String str;
			text = new String[1000];
			text_index = 0;
			while (in.hasNext()) {
				str = in.next();
				int start = 0;
				int fin = 0;
				// System.out.println(str);
				while (fin <= str.length()) {
					// word parsing from start to fin
					if (fin == str.length() || str.charAt(fin) < 'a'
							|| str.charAt(fin) > 'z') {
						String substr = str.subSequence(start, fin).toString();
						// System.out.println(substr);
						int i;
						for (i = 0; i < text_index; i++)
							if (text[i].equals(substr))
								break;
						if (i == text_index & substr.length() > 0)
							text[text_index++] = new String(substr);

						start = fin + 1;
					}
					fin++;
				}
			}
			// for(int i=0;i<text_index;i++)System.out.println(text[i]);
			// System.out.println(text_index);
			in.close();
		} catch (FileNotFoundException r) {
		}
	}

	public static void decry() {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(
					"ciphertext.txt"));
			BufferedWriter wr = new BufferedWriter(new FileWriter(
					"plaintext.txt"));
			char rkey[] = new char[26];
			for (char i = 'a'; i <= 'z'; i++) {
				if (key.charAt(i - 'a') > 'z' || key.charAt(i - 'a') < 'a')
					continue;
				rkey[key.charAt(i - 'a') - 'a'] = i;
			}
			System.out.println(rkey);
			StringBuffer strb;
			String str;
			while (((str = bf.readLine())) != null) {
				strb = new StringBuffer(str);
				// System.out.println(strb);
				// String ans;
				for (int i = 0; i < strb.length(); i++) {
					if (strb.charAt(i) >= 'a' && strb.charAt(i) <= 'z') {
						strb.setCharAt(i, rkey[strb.charAt(i) - 'a']);
					}

				}
				System.out.println(strb.toString());
				wr.write(strb.toString());
				wr.newLine();
			}
			// keyf.close();
			wr.close();
			bf.close();

		} catch (IOException e) {

		}
	}
}

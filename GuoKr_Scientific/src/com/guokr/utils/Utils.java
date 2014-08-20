package com.guokr.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
	public static void CopyStream(InputStream is, OutputStream os) {
		// final int buffer_size = 1024;
		// try {
		// byte[] bytes = new byte[buffer_size];
		// for (;;) {
		// int count = is.read(bytes, 0, buffer_size);
		// if (count == -1)
		// break;
		// os.write(bytes, 0, count);
		// is.close();
		// os.close();
		// }
		// } catch (Exception ex) {
		// }

		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		try {
			while ((len = is.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			os.write(outSteam.toByteArray());
			outSteam.close();
			is.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
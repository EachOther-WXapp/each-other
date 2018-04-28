package com.wutong.weixin.utils.encrypt;

/**
 *
 * 字节和十六进制字符串互转
 */
public class ByteHexUtil {



    /**
     * 字节数组转16进制字符串 方式一
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        char[] res = new char[bytes.length * 2]; // 每个byte对应两个字符
        final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        for (int i = 0, j = 0; i < bytes.length; i++) {
            res[j++] = hexDigits[bytes[i] >> 4 & 0x0f]; // 先存byte的高4位
            res[j++] = hexDigits[bytes[i] & 0x0f]; // 再存byte的低4位
        }
        return new String(res);
    }

    /**
     * 字节数组转16进制字符串 方式二
     * @param bytes
     * @return
     */
    public static String byteToHex2(byte[] bytes) {
        StringBuilder buf = new StringBuilder();
        for (byte aByte : bytes) {
            String s = Integer.toHexString(aByte & 0xff);
            if (s.length() == 1) buf.append("0");//转换成16进制ACCSII,不足两位高位补零
            buf.append(s);
        }
        return buf.toString();
    }


    /**
     * 16进制字符串转字节数组
     * @param hexString
     * @return
     */
    public static byte[] hexToByte(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        String hexDigits = "0123456789abcdef";
        for (int i = 0; i < length; i++) {
            int pos = i * 2; // 两个字符对应一个byte
            int h = hexDigits.indexOf(hexChars[pos]) << 4; // 注1
            int l = hexDigits.indexOf(hexChars[pos + 1]); // 注2
            if(h == -1 || l == -1) { // 非16进制字符
                return null;
            }
            bytes[i] = (byte) (h | l);
        }
        return bytes;
    }

}

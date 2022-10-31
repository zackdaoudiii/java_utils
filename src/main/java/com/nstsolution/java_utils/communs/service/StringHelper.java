package com.nstsolution.java_utils.communs.service;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    /**
     * Right pad with spaces up to length characters.
     * null wil be converted to spaces
     *
     * @param text   Initial value
     * @param length column's length
     * @return Right padded value
     */
    public static String rightPad(final String text, final int length) {
        // Ignore Sonar warning, this format is OK
        return String.format("%-" + length + "s", text == null ? "" : text);
    }

    /**
     * Left pad with 0 up to length characters.
     * null will be converted to 0.
     *
     * @param number Initial value
     * @param length data's length
     * @return Left padded value
     */
    public static String leftPad(final Integer number, final int length) {
        // Ignore Sonar warning, this format is OK
        return String.format("%0" + length + "d", number == null ? 0 : number);
    }


//    public static Integer parseInt(final String text) {
//        final Integer result;
//        if (StringUtils.isBlank(text)) {
//            result = null;
//        } else {
//            result = Integer.parseInt(text.trim());
//        }
//        return result;
//    }
//
//    public static String getCleanedCode(final Object code) {
//        return upperCase(trim(code));
//    }
//
//    public static String getCleanedCode(final String code) {
//        return upperCase(trim(code));
//    }
//
//    public static boolean hasText(final String text) {
//        return StringUtils.isNotBlank(text);
//    }
//
//    public static boolean hasText(final Object object) {
//        if (object == null) {
//            return false;
//        } else {
//            return hasText(object.toString());
//        }
//    }
//
//    public static boolean isBlank(final String text) {
//        return StringUtils.isBlank(text);
//    }
//
//    public static boolean isBlank(final Object object) {
//        if (object == null) {
//            return true;
//        } else {
//            return StringUtils.isBlank(object.toString());
//        }
//    }
//
//    public static boolean areBlank(final Object... objects) {
//        boolean areBlank = true;
//        if (objects != null) {
//            for (Object object : objects) {
//                areBlank &= isBlank(object);
//            }
//        }
//        return areBlank;
//    }
//
//    public static String trim(final Object text) {
//        if (text == null) {
//            return null;
//        } else {
//            return trim(text.toString());
//        }
//    }
//
//    public static String trim(final String text) {
//        return StringUtils.trim(text);
//    }
//
//    public static String upperCase(final String text) {
//        return StringUtils.upperCase(text);
//    }
//
//    public static String substringBefore(final String text, final String separator) {
//        return StringUtils.substringBefore(text, separator);
//    }
//
//    public static String deleteControlCharacters(final String text) {
//        return text.replaceAll("[\\p{Cntrl}]", "");
//    }

    private static final String FOLDER_SEPARATOR = "/";

    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

    private static final String TOP_PATH = "..";

    private static final String CURRENT_PATH = ".";

    private static final char EXTENSION_SEPARATOR = '.';

    private static final Map<String,String> XML = new HashMap<String,String>();
    static{
        XML.put("apos", "'");
        XML.put("quot", "\"");
        XML.put("amp", "&");
        XML.put("lt", "<");
        XML.put("gt", ">");
    }


    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String getExtension(String str) {
        if(str == null) return null;
        int i = str.lastIndexOf('.');
        if(i >= 0) {
            return str.substring(i+1);
        }
        return null;
    }

    public static String insertBefore(String content,String compareToken,String insertString) {
        if(content.indexOf(insertString) >= 0) return content;
        int index = content.indexOf(compareToken);
        if(index >= 0) {
            return new StringBuffer(content).insert(index,insertString).toString();
        }else {
            throw new IllegalArgumentException("not found insert location by compareToken:"+compareToken+" content:"+content);
        }
    }

    public static String insertAfter(String content,String compareToken,String insertString) {
        if(content.indexOf(insertString) >= 0) return content;
        int index = content.indexOf(compareToken);
        if(index >= 0) {
            return new StringBuffer(content).insert(index+compareToken.length(),insertString).toString();
        }else {
            throw new IllegalArgumentException("not found insert location by compareToken:"+compareToken+" content:"+content);
        }
    }

    /**
     * Count the occurrences of the substring in string s.
     * @param str string to search in. Return 0 if this is null.
     * @param sub string to search for. Return 0 if this is null.
     */
    public static int countOccurrencesOf(String str, String sub) {
        if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
            return 0;
        }
        int count = 0;
        int pos = 0;
        int idx;
        while ((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        return count;
    }

    public static boolean contains(String str,String... keywords) {
        if(str == null) return false;
        if(keywords == null) throw new IllegalArgumentException("'keywords' must be not null");

        for(String keyword : keywords) {
            if(str.contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static String defaultString(Object value) {
        if(value == null) {
            return "";
        }
        return value.toString();
    }

    public static String defaultIfEmpty(Object value,String defaultValue) {
        if(value == null || "".equals(value)) {
            return defaultValue;
        }
        return value.toString();
    }

    public static String makeAllWordFirstLetterUpperCase(String sqlName) {
        String[] strs = sqlName.toLowerCase().split("_");
        String result = "";
        String preStr = "";
        for(int i = 0; i < strs.length; i++) {
            if(preStr.length() == 1) {
                result += strs[i];
            }else {
                result += capitalize(strs[i]);
            }
            preStr = strs[i];
        }
        return result;
    }

    public static int indexOfByRegex(String input,String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if(m.find()) {
            return m.start();
        }
        return -1;
    }



    public static String replace(String inString, String oldPattern, String newPattern) {
        if (inString == null) {
            return null;
        }
        if (oldPattern == null || newPattern == null) {
            return inString;
        }

        StringBuffer sbuf = new StringBuffer();
        // output StringBuffer we'll build up
        int pos = 0; // our position in the old string
        int index = inString.indexOf(oldPattern);
        // the index of an occurrence we've found, or -1
        int patLen = oldPattern.length();
        while (index >= 0) {
            sbuf.append(inString.substring(pos, index));
            sbuf.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sbuf.append(inString.substring(pos));

        // remember to append any characters to the right of a match
        return sbuf.toString();
    }
    /**����ĸ��copy from spring*/
    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    /**����ĸСдcopy from spring*/
    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }
    /**copy from spring*/
    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str.length());
        if (capitalize) {
            buf.append(Character.toUpperCase(str.charAt(0)));
        }
        else {
            buf.append(Character.toLowerCase(str.charAt(0)));
        }
        buf.append(str.substring(1));
        return buf.toString();
    }

    private static final Random RANDOM = new Random();
    public static String randomNumeric(int count) {
        return random(count, false, true);
    }

    public static String random(int count, boolean letters, boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }

    public static String random(int count, int start, int end, boolean letters, boolean numbers) {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }

    public static String random(int count, int start, int end, boolean letters,
                                boolean numbers, char[] chars, Random random) {
        if (count == 0) {
            return "";
        } else if (count < 0) {
            throw new IllegalArgumentException(
                    "Requested random string length " + count
                            + " is less than 0.");
        }
        if ((start == 0) && (end == 0)) {
            end = 'z' + 1;
            start = ' ';
            if (!letters && !numbers) {
                start = 0;
                end = Integer.MAX_VALUE;
            }
        }

        char[] buffer = new char[count];
        int gap = end - start;

        while (count-- != 0) {
            char ch;
            if (chars == null) {
                ch = (char) (random.nextInt(gap) + start);
            } else {
                ch = chars[random.nextInt(gap) + start];
            }
            if ((letters && Character.isLetter(ch))
                    || (numbers && Character.isDigit(ch))
                    || (!letters && !numbers)) {
                if (ch >= 56320 && ch <= 57343) {
                    if (count == 0) {
                        count++;
                    } else {
                        // low surrogate, insert high surrogate after putting it
                        // in
                        buffer[count] = ch;
                        count--;
                        buffer[count] = (char) (55296 + random.nextInt(128));
                    }
                } else if (ch >= 55296 && ch <= 56191) {
                    if (count == 0) {
                        count++;
                    } else {
                        // high surrogate, insert low surrogate before putting
                        // it in
                        buffer[count] = (char) (56320 + random.nextInt(128));
                        count--;
                        buffer[count] = ch;
                    }
                } else if (ch >= 56192 && ch <= 56319) {
                    // private high surrogate, no effing clue, so skip it
                    count++;
                } else {
                    buffer[count] = ch;
                }
            } else {
                count++;
            }
        }
        return new String(buffer);
    }

    public static String removeEndWiths(String inputString,String... endWiths) {
        for(String endWith : endWiths) {
            if(inputString.endsWith(endWith)) {
                return inputString.substring(0,inputString.length() - endWith.length());
            }
        }
        return inputString;
    }


    public static String join(List list, String seperator) {
        return join(list.toArray(new Object[0]),seperator);
    }

    public static String replace(int start, int end, String str,String replacement) {
        String before = str.substring(0,start);
        String after = str.substring(end);
        return before + replacement + after;
    }

    public static String join(Object[] array, String seperator) {
        if(array == null) return null;
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if(i != array.length - 1)  {
                result.append(seperator);
            }
        }
        return result.toString();
    }

}

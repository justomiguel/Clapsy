/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frre.library;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import com.frre.library.data.Constants;
import org.apache.commons.io.IOUtils;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import static com.frre.library.data.Constants.*;
import static com.frre.library.data.Constants.SPACE;

/**
 *
 * @author Cleo
 */
public class Utils {

    private static boolean DEBUG_MODE = false;
    public static HashMap<String, String> theVariables = new HashMap<String, String>();


    public static <T> T tranformAccordingType(Class<T> type, String string) {

        if (type.isAssignableFrom(String.class)) {
            return (T) string;
        } else if (type.isAssignableFrom(Date.class)) {
            String[] date = string.split(Constants.DATE_SEPARATOR);
            int year = Integer.parseInt(date[2].trim());
            int month = Integer.parseInt(date[1].trim());
            int day = Integer.parseInt(date[0].trim());
            Date d;
            Calendar cal = GregorianCalendar.getInstance();
            cal.set( year, month, day);
            d = cal.getTime();
            return (T) d;
        } else if (type.isAssignableFrom(Fecha.class)) {
            return (T) new Fecha(string.trim());
        } else if (type.isAssignableFrom(double.class) || (type.isAssignableFrom(Double.class))) {
            return (T) new Double(string.trim().replaceAll(Constants.COMMA, Constants.DOT));
        } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
            return (T) new Float(string.trim().replaceAll(Constants.COMMA, Constants.DOT));
        } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
            //takeout all spaces
            return (T) new Integer(string.trim());
        }
        return null;
        //To change body of generated methods, choose Tools | Templates.
    }

    public static <T> T getValueACcordingType(Class<T> type) {

        if (type.isAssignableFrom(String.class)) {
            return (T) Generador.generarStringAleatorio();
        } else if (type.isAssignableFrom(Date.class)) {
            int year = Generador.generarEnteroAleatorio(1900, 2015);
            int month = Generador.generarEnteroAleatorio(1, 12);
            int day = Generador.generarEnteroAleatorio(1, 31);
            Date d;
            Calendar cal = GregorianCalendar.getInstance();
            cal.set( year, month, day);
            d = cal.getTime();
            return (T) d;
        } else if (type.isAssignableFrom(Fecha.class)) {
            int year = Generador.generarEnteroAleatorio(1900, 2015);
            int month = Generador.generarEnteroAleatorio(1, 12);
            int day = Generador.generarEnteroAleatorio(1, 31);
            Date d;
            Calendar cal = GregorianCalendar.getInstance();
            cal.set( year, month, day);
            d = cal.getTime();
            SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
            return (T) new Fecha(sm.format(d));
        } else if (type.isAssignableFrom(double.class) || (type.isAssignableFrom(Double.class))) {
            return (T) Generador.generarDecimalAleatorio(0,500);
        } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
            return (T) new Float(Generador.generarEnteroAleatorio(0, 500));
        } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
            //takeout all spaces
            Integer integer = Generador.generarEnteroAleatorio(0, 500);
            return (T) integer;
        }
        return null;
        //To change body of generated methods, choose Tools | Templates.
    }

    public static <T> T getValueAccordingTypeAndMethodName(Class<T> type, String methodName) {
        methodName = methodName.toLowerCase();
        if (type.isAssignableFrom(String.class)) {
            if (methodName.contains("nom") && methodName.contains("ap")){
                return (T) String.valueOf(Generador.generarNombreAleatorio()+","+Generador.generarApellidoAleatorio());
            } else if (methodName.contains("apellido")){
                return (T) Generador.generarApellidoAleatorio();
            } else if (methodName.contains("nombre")){
                return (T) Generador.generarNombreAleatorio();
            }  else if (methodName.contains("provincia") || methodName.contains("pcia")){
                return (T) Generador.generarPciaAleatorio();
            }  else if (methodName.contains("pais") || methodName.contains("country")){
                return (T) Generador.generarPaisAleatorio();
            }   else if (methodName.contains("patente") || methodName.contains("pat")){
                return (T) Generador.generarPaisAleatorio();
            }  else if (methodName.contains("localidad") || methodName.contains("loc") || methodName.contains("local")){
                return (T) Generador.generarLocalidadAleatorio();
            }  else if (methodName.contains("titulo") || methodName.contains("tit") || methodName.contains("materia") || methodName.contains("mat")){
                return (T) Generador.generarPalabraConArticuloAleatoria();
            }
            return (T) Generador.generarPalabraSinArticuloAleatoria();
        } else if (type.isAssignableFrom(Date.class)) {
            int year = Generador.generarEnteroAleatorio(1900, 2015);
            int month = Generador.generarEnteroAleatorio(1, 12);
            int day = Generador.generarEnteroAleatorio(1, 31);
            Date d;
            Calendar cal = GregorianCalendar.getInstance();
            cal.set( year, month, day);
            d = cal.getTime();
            return (T) d;
        } else if (type.isAssignableFrom(Fecha.class)) {
            int year = Generador.generarEnteroAleatorio(1900, 2015);
            int month = Generador.generarEnteroAleatorio(1, 12);
            int day = Generador.generarEnteroAleatorio(1, 31);
            Date d;
            Calendar cal = GregorianCalendar.getInstance();
            cal.set( year, month, day);
            d = cal.getTime();
            SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
            return (T) new Fecha(sm.format(d));
        } else if (type.isAssignableFrom(double.class) || (type.isAssignableFrom(Double.class))) {
            return (T) Generador.generarDecimalAleatorio(0,500);
        } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
            return (T) new Float(Generador.generarEnteroAleatorio(0, 500));
        } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
            //takeout all spaces
            Integer integer = Generador.generarEnteroAleatorio(0, 500);
            if (methodName.contains("dni")){
                integer = Generador.generarDNIAleatorio();
            } else if (methodName.contains("legajo") || methodName.contains("leg")){
                integer = Generador.generarLegajoAleatorio();
            }
            return (T) integer;
        }
        return null;
        //To change body of generated methods, choose Tools | Templates.
    }
    
    public static <T> T[] copyArray(T[] vector){
        T[] another = (T[]) Array.newInstance(vector.getClass().getComponentType(), vector.length);
        System.arraycopy(vector, 0, another, 0, vector.length);
        return another;
    }

    public static String getSetMethod(String fieldName) {
        // TODO Auto-generated method stub
        String firstWithCapitalLetter = fieldName.toUpperCase().substring(0, 1);
        String restOfMethodName = fieldName.substring(1, fieldName.length());
        return Constants.SET + firstWithCapitalLetter + restOfMethodName;
    }
    
    public static String getGetMethod(String fieldName) {
        // TODO Auto-generated method stub
        String firstWithCapitalLetter = fieldName.toUpperCase().substring(0, 1);
        String restOfMethodName = fieldName.substring(1, fieldName.length());
        String methodName = Constants.GET + firstWithCapitalLetter + restOfMethodName;
        return methodName;
    }

    public static void handleException(Exception ex) {
        System.out.println(Constants.EXCEPCION_OCURRIDA_ + ex.getClass().getName()+" "+ex.getMessage());
    }
    
    public static IBinaryTree buildTree(Class theClass, Object[] inorder, Object[] postorder) {
        try {
            if (inorder.length == 0)
                return null;
            if (inorder.length == 1){
                IBinaryTree tree = ((IBinaryTree) theClass.newInstance());
                tree.setValue(inorder[0]);
                return tree;
            }
            // the last item in postorder is root
            IBinaryTree root = ((IBinaryTree) theClass.newInstance());
            root.setValue(postorder[postorder.length - 1]);

            int i = inorder.length - 1;
            for (; !inorder[i].equals(root.getValue()); i--)
                ;

            // inorder.length == postorder.length
            if (i < inorder.length - 1) {
                root.setRighSon(buildTree(theClass,
                        Arrays.copyOfRange(inorder, i + 1, inorder.length),
                        Arrays.copyOfRange(postorder, i, postorder.length - 1)));            
            }
            if (i > 0) {
                root.setLeftSon(buildTree(theClass, 
                        Arrays.copyOfRange(inorder, 0, i),
                        Arrays.copyOfRange(postorder, 0, i)));
            }

            return root;
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
}

    public static boolean esMenor(Object obj1, Object obj2) {
        return  compareTo(obj1, obj2) < 0;
    }

    public static int compareTo(Object obj1, Object obj2) {
        try {
            Object localReg = (Object) obj1.getClass().newInstance();
            int currentFieldNumber = 0;
            int claves = 0;
            for (Field f : obj1.getClass().getDeclaredFields()) {
                if (f.getAnnotation(Clave.class)!=null){
                    claves+=1;
                }
            }

            Field[] campos = obj1.getClass().getDeclaredFields();
            for (int i = 0; i < claves; i++) {
                Field f = campos[i];
                Method method = obj1.getClass().getDeclaredMethod(Utils.getGetMethod(f.getName()));
                Method method2 = obj2.getClass().getDeclaredMethod(Utils.getGetMethod(f.getName()));
                Comparable comparable = (Comparable) method.invoke(obj1);
                Comparable comparable2 = (Comparable) method2.invoke(obj2);
                int comparation = comparable.compareTo(comparable2);
                if (comparation != 0) {
                    return comparation;
                }
            }
            return 0;
        } catch (Exception e) {

        }
        return 0;
    }

    public static boolean esMayor(Object someString, Object provincia1) {
        return compareTo(someString, provincia1) > 0;
    }

    public static boolean esIgual(String someString, String provincia1) {
        return compareTo(someString, provincia1) == 0;
    }

    public static String getType(String type) {
        if (type.equalsIgnoreCase("Alfanumerico") || type.equalsIgnoreCase("cadena") || type.equalsIgnoreCase("char")|| type.equalsIgnoreCase("caracter") ){
            return "String";
        } else if (type.equalsIgnoreCase("entero")){
            return "int";
        } else if (type.equalsIgnoreCase("real")){
            return "double";
        } else {
            return "boolean";
        }
    }

    public static void compilationAndExec(StringWriter writer) throws MalformedURLException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        JavaFileObject file = new JavaSourceFromString("Prueba", writer.toString());

        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

        boolean success = task.call();

        System.out.println("Estado Compilacion: " + (success?"Correcto":"Incorrecto revisar algoritmo"));

        if (DEBUG_MODE){
            System.out.println(writer.toString());
        }

        if (success) {
            try {

                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File("").toURI().toURL()});
                Class.forName("Prueba", true, classLoader).getDeclaredMethod("main", new Class[]{String[].class}).invoke(null, new Object[]{null});

            } catch (ClassNotFoundException e) {
                System.err.println("Class not found: " + e);
            } catch (NoSuchMethodException e) {
                System.err.println("No such method: " + e);
            } catch (IllegalAccessException e) {
                System.err.println("Illegal access: " + e);
            } catch (InvocationTargetException e) {
                System.err.println("Invocation target: " + e);
            }
        }
    }

    public static void tryToExtractVariables(String everything, Writer writer) throws IOException {
        String ambiente = everything.substring(getPositionOf(AMBIENTE, everything) + AMBIENTE.length(), getPositionOf(ALGORITMO, everything));
        ambiente = ambiente.trim();
        String[] variables = ambiente.split(";");

        writer.write(" static Scanner sc = new Scanner(System.in); \n");

        for (int i = 0; i < variables.length; i++) {
            String[] variable = variables[i].split(":");
            String name = variable[0].trim();
            String dataType = Utils.getType(variable[1].trim());

            theVariables.put(name, dataType);

            writer.write(" static " + dataType + SPACE + name + END_LINE + NEW_LINE);
        }
    }

    public static int getPositionOf(String ambiente, String everything) {
        if (everything.indexOf(ambiente) != -1) {
            return everything.indexOf(ambiente);
        } else {
            return everything.indexOf(ambiente.toLowerCase());
        }
    }

    public static void addFooter(Writer writer) throws IOException {
        writer.write("}");
    }

    public static void addHeader(Writer writer) throws IOException {
        writer.write("import java.util.*; \n");
        writer.write("public class Prueba { \n \n");
        writer.write("//ambiente \n \n");
    }

    public static void tryToExtractAction(String everything, Writer writer) throws IOException {
        String algoritmo = everything.substring(getPositionOf(ALGORITMO, everything) + ALGORITMO.length(), getPositionOf(FIN_ALGORITMO, everything));
        String[] actions = algoritmo.replaceAll(";", "").split("\n");

        writer.write("//algoritmo \n");
        writer.write("public static void main(String[] args){ \n");

        int i = 0;
        ArrayList<String> pilaAcciones = new ArrayList<String>();
        while (i < actions.length) {
            String line = actions[i].trim();
            line = line.replace(" mod ", " % ");
            if (!line.startsWith("//")){
                if (!detectAction(line, writer)) {
                    specialAction(line, writer, pilaAcciones);
                }
            }
            i++;
        }

        writer.write("}\n");


    }

    public static void specialAction(String line, Writer writer, ArrayList<String> pilaAcciones) throws IOException {
        if (line.contains(SI) && line.contains(ENTONCES) && !line.contains(SINO)) {
            String logic = line.substring(getPositionOf(SI, line) + SI.length(), getPositionOf(ENTONCES, line));
            logic = logic.replaceAll(" = ", " == ").replaceAll(" <> ", " != ");
            logic = replaceWithEquals(logic);
            writer.write("if (" + logic + "){\n");
        } else if (line.contains(FIN)) {
            writer.write("}\n");
        }else if (line.contains(REPETIR)) {
            writer.write("do { \n");
        }  else if (line.trim().equalsIgnoreCase(SINO)) {
            writer.write("} else { \n");
        } else if (line.contains(SINO_SI)) {
            String logic = line.substring(getPositionOf(SINO_SI, line) + SINO_SI.length(), getPositionOf(ENTONCES, line));
            logic = logic.replaceAll(" = ", " == ").replaceAll(" <> ", " != ");
            logic = replaceWithEquals(logic);
            writer.write("} else if (" + logic + "){\n");
        } else if (line.contains(HASTA) && line.contains(")")){
            String logic = line.substring(getPositionOf(HASTA, line) + HASTA.length(), getPositionOf(")", line));
            logic = logic.replaceAll(" = ", " == ").replaceAll(" <> ", " != ");
            logic = replaceWithEquals(logic);
            writer.write(" } while (" + logic + ");\n");
        } else if (line.contains(MIENTRAS) && line.contains(HACER)){
            String logic = line.substring(getPositionOf(MIENTRAS, line) + MIENTRAS.length(), getPositionOf(HACER, line));
            logic = logic.replaceAll(" = ", " == ").replaceAll(" <> ", " != ");
            logic = replaceWithEquals(logic);
            writer.write("while (" + logic + "){\n");
        } else if (line.contains(PARA) && line.contains(")hacer")){
            String logic = line.substring(getPositionOf(HASTA, line) + HASTA.length(), getPositionOf(")", line));
            String[] actions = logic.split(",");
            String letter = actions[0].split("hasta")[0].split("=")[0].trim();
            String init = actions[0].split("hasta")[0].split("=")[1].trim();
            String tope = actions[0].split("hasta")[1].trim();
            String aumento = actions[1].substring(actions[1].indexOf(letter)+letter.length()).trim();
            if (aumento.contains("=")){
                aumento = aumento.replace(":=","=");
            }
            writer.write("for("+letter+"="+init+";"+letter+" <= "+tope+";"+letter+aumento+") { \n");
        }
    }

    public static String replaceWithEquals(String logic) {
        StringBuilder builder = new StringBuilder();
        if (logic.contains(" ^ ")) {
            String[] actions = logic.split(Pattern.quote(" ^ "));
            for (int i = 0; i < actions.length; i++) {
                String subline = actions[i];
                if (subline.contains(" | ")) {
                    String[] ires = subline.split(Pattern.quote(" | "));
                    for (int j = 0; j < ires.length; j++) {
                        String[] line = ires[j].split(" == ");
                        if (line.length > 1 && theVariables.get(line[0]) != null && theVariables.get(line[0]).trim().equalsIgnoreCase("String")) {
                            builder.append(line[0].trim() + ".equalsIgnoreCase(" + line[1].trim() + ")");
                        } else {
                            builder.append(ires[j]);
                        }
                        if (j != ires.length-1){
                            builder.append(" || ");
                        }
                    }
                } else {
                    String[] line = subline.split(" == ");
                    if (line.length > 1 && theVariables.get(line[0]) != null && theVariables.get(line[0]).trim().equalsIgnoreCase("String")) {
                        builder.append(line[0].trim() + ".equalsIgnoreCase(" + line[1].trim() + ")");
                    } else {
                        builder.append(subline);
                    }
                }
                if (i != actions.length-1){
                    builder.append(" && ");
                }
            }
        } else if (logic.contains(" | ")) {
            String[] actions = logic.split(Pattern.quote(" | "));
            for (int i = 0; i < actions.length; i++) {
                String subline = actions[i];
                String[] line = subline.split(" == ");
                if (line.length > 1 && theVariables.get(line[0]) != null &&  theVariables.get(line[0]).trim().equalsIgnoreCase("String")) {
                    builder.append(line[0].trim() + ".equalsIgnoreCase(" + line[1].trim() + ")");
                } else {
                    builder.append(subline);
                }
                if (i != actions.length-1){
                    builder.append(" || ");
                }
            }
        } else {
            String subline = logic;
            String[] line = subline.split(" == ");
            if (line.length > 1 && theVariables.get(line[0]) != null && theVariables.get(line[0]).trim().equalsIgnoreCase("String")) {
                builder.append(line[0].trim() + ".equalsIgnoreCase(" + line[1].trim() + ")");
            } else {
                builder.append(subline);
            }
        }

        return builder.toString();
    }

    public static boolean detectAction(String line, Writer writer) throws IOException {
        if (line.contains(ESCRIBIR) || line.contains(ESCRIBIR.toLowerCase())) {
            String titAMostrar = line.substring(getPositionOf(ESCRIBIR, line) + ESCRIBIR.length(), getPositionOf(")", line));
            if (titAMostrar.contains(",\"")) {
                String[] concat = titAMostrar.split(",");
                writer.write("System.out.println(");
                for (int i = 0; i < concat.length; i++) {
                    writer.write(concat[i]);
                    if (i != concat.length - 1) {
                        writer.write("+");
                    }
                }
                writer.write("); \n");
            } else {
                writer.write("System.out.println(" + titAMostrar + "); \n");
            }
            return true;
        } else if (line.contains(LEER) || line.contains(LEER.toLowerCase())) {
            String titALeer = line.substring(getPositionOf(LEER, line) + LEER.length(), getPositionOf(")", line));
            if (titALeer.contains(",")) {
                String[] vars = titALeer.split(",");
                for (int i = 0; i < vars.length; i++) {
                    writer.write(vars[i] + " = sc.next" + getTypeForInput(vars[i].trim()) + "();\n");
                }
            } else {
                writer.write(titALeer + " = sc.next" + getTypeForInput(titALeer) + "();\n");
            }
            return true;
        } else if (line.contains(":=") && !line.contains(PARA)) {
            String[] assigns = line.split(":=");
            writer.write(assigns[0].trim() + SPACE + "=" + SPACE + assigns[1].trim() + ";\n");
            return true;
        }
        return false;
    }

    public static String getTypeForInput(String var) {
        if (theVariables.get(var).equalsIgnoreCase("String")) {
            return "";
        }
        String name = theVariables.get(var);
        StringBuilder sb = new StringBuilder();
        sb.append(name.substring(0, 1).toUpperCase());
        sb.append(name.substring(1).toLowerCase());
        return sb.toString();
    }

    public static void analizeAndExec(String file, boolean debugMode) throws IOException {
        FileInputStream inputStream = null;
        StringWriter writer = null;
        try {
            DEBUG_MODE = debugMode;
            inputStream = new FileInputStream(file);
            writer = new StringWriter();
            PrintWriter out = new PrintWriter(writer);
            String everything = IOUtils.toString(inputStream);
            //public class
            addHeader(writer);
            //
            tryToExtractVariables(everything, writer);
            //main
            tryToExtractAction(everything, writer);
            //final {
            addFooter(writer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
        compilationAndExec(writer);
    }

    public static int findWord(String[] args, String word) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase(word)){
                return i;
            }
        }
        return -1;
    }
}

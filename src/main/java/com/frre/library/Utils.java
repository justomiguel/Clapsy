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
import com.frre.practica.isi.algoritmos.MyLogger;
import org.apache.commons.io.IOUtils;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import static com.frre.library.data.Constants.*;
import static com.frre.library.data.Constants.SPACE;

/**
 * @author Cleo
 */
public class Utils {

    public static final String SI = "SI";
    public static final String SEGUN = "Segun(";
    public static final String ARRANCAR = "Arrancar(";
    public static final String AVANZAR = "Avanzar(";
    public static final String CERRAR = "Cerrar(";
    public static final String CREAR = "Crear(";
    private static  int SEGUN_COUNTER = 0;
    public static boolean DEBUG_MODE = false;
    public static HashMap<String, String> theVariables = new HashMap<String, String>();
    private static String SEGUN_VARIABLE;
    private static HashMap<String, Integer> sMapaSecuencias = new HashMap<>();
    private static List<String> secuenciasPorCrear = new ArrayList<>();

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
            cal.set(year, month, day);
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
            cal.set(year, month, day);
            d = cal.getTime();
            return (T) d;
        } else if (type.isAssignableFrom(Fecha.class)) {
            int year = Generador.generarEnteroAleatorio(1900, 2015);
            int month = Generador.generarEnteroAleatorio(1, 12);
            int day = Generador.generarEnteroAleatorio(1, 31);
            Date d;
            Calendar cal = GregorianCalendar.getInstance();
            cal.set(year, month, day);
            d = cal.getTime();
            SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
            return (T) new Fecha(sm.format(d));
        } else if (type.isAssignableFrom(double.class) || (type.isAssignableFrom(Double.class))) {
            return (T) Generador.generarDecimalAleatorio(0, 500);
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
            if (methodName.contains("nom") && methodName.contains("ap")) {
                return (T) String.valueOf(Generador.generarNombreAleatorio() + "," + Generador.generarApellidoAleatorio());
            } else if (methodName.contains("apellido")) {
                return (T) Generador.generarApellidoAleatorio();
            } else if (methodName.contains("nombre")) {
                return (T) Generador.generarNombreAleatorio();
            } else if (methodName.contains("provincia") || methodName.contains("pcia")) {
                return (T) Generador.generarPciaAleatorio();
            } else if (methodName.contains("pais") || methodName.contains("country")) {
                return (T) Generador.generarPaisAleatorio();
            } else if (methodName.contains("patente") || methodName.contains("pat")) {
                return (T) Generador.generarPaisAleatorio();
            } else if (methodName.contains("localidad") || methodName.contains("loc") || methodName.contains("local")) {
                return (T) Generador.generarLocalidadAleatorio();
            } else if (methodName.contains("titulo") || methodName.contains("tit") || methodName.contains("materia") || methodName.contains("mat")) {
                return (T) Generador.generarPalabraConArticuloAleatoria();
            }
            return (T) Generador.generarPalabraSinArticuloAleatoria();
        } else if (type.isAssignableFrom(Date.class)) {
            int year = Generador.generarEnteroAleatorio(1900, 2015);
            int month = Generador.generarEnteroAleatorio(1, 12);
            int day = Generador.generarEnteroAleatorio(1, 31);
            Date d;
            Calendar cal = GregorianCalendar.getInstance();
            cal.set(year, month, day);
            d = cal.getTime();
            return (T) d;
        } else if (type.isAssignableFrom(Fecha.class)) {
            int year = Generador.generarEnteroAleatorio(1900, 2015);
            int month = Generador.generarEnteroAleatorio(1, 12);
            int day = Generador.generarEnteroAleatorio(1, 31);
            Date d;
            Calendar cal = GregorianCalendar.getInstance();
            cal.set(year, month, day);
            d = cal.getTime();
            SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
            return (T) new Fecha(sm.format(d));
        } else if (type.isAssignableFrom(double.class) || (type.isAssignableFrom(Double.class))) {
            return (T) Generador.generarDecimalAleatorio(0, 500);
        } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
            return (T) new Float(Generador.generarEnteroAleatorio(0, 500));
        } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
            //takeout all spaces
            Integer integer = Generador.generarEnteroAleatorio(0, 500);
            if (methodName.contains("dni")) {
                integer = Generador.generarDNIAleatorio();
            } else if (methodName.contains("legajo") || methodName.contains("leg")) {
                integer = Generador.generarLegajoAleatorio();
            }
            return (T) integer;
        }
        return null;
        //To change body of generated methods, choose Tools | Templates.
    }

    public static <T> T[] copyArray(T[] vector) {
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
        System.out.println(Constants.EXCEPCION_OCURRIDA_ + ex.getClass().getName() + " " + ex.getMessage());
    }

    public static IBinaryTree buildTree(Class theClass, Object[] inorder, Object[] postorder) {
        try {
            if (inorder.length == 0)
                return null;
            if (inorder.length == 1) {
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
        return compareTo(obj1, obj2) < 0;
    }

    public static int compareTo(Object obj1, Object obj2) {
        try {
            Object localReg = (Object) obj1.getClass().newInstance();
            int currentFieldNumber = 0;
            int claves = 0;
            for (Field f : obj1.getClass().getDeclaredFields()) {
                if (f.getAnnotation(Clave.class) != null) {
                    claves += 1;
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
        if (type.equalsIgnoreCase("Alfanumerico") || type.equalsIgnoreCase("cadena") || type.equalsIgnoreCase("char") || type.equalsIgnoreCase("caracter")) {
            return "String";
        } else if (type.equalsIgnoreCase("entero")) {
            return "int";
        } else if (type.toLowerCase().contains("Secuencia de caracter".toLowerCase())) {
            return "String[]";
        } else if (type.equalsIgnoreCase("real")) {
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

        System.out.println("Estado Compilacion: " + (success ? "Correcto" : "Incorrecto revisar algoritmo"));

        MyLogger.log(writer.toString());

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

    public static void tryToExtractVariables(String everything, String secuenciaArchivoContents, Writer writer) throws IOException, AlgorithmException {
        try{
            String ambiente = everything.substring(getPositionOf(AMBIENTE, everything) + AMBIENTE.length(), getPositionOf(ALGORITMO, everything)).replaceAll(";", "");
            ambiente = ambiente.trim();
            String[] variables = ambiente.split("\n");

            writer.write(" static Scanner sc = new Scanner(System.in); \n");

            for (int i = 0; i < variables.length; i++) {
                String[] variable = variables[i].split(":");
                String name = variable[0].trim();
                String dataType = Utils.getType(variable[1].trim());

                theVariables.put(name, dataType);

                if (dataType.equals("String[]")){
                    if (secuenciaArchivoContents == null){
                        throw new AlgorithmException("No se especifico entrada de secuencia");
                    } else {
                        writer.write(" static " + dataType + SPACE + name + " = new String[]{");
                        for (int j = 0; j < secuenciaArchivoContents.length(); j++) {
                            writer.write("\""+String.valueOf(secuenciaArchivoContents.charAt(j))+"\",");
                        }
                        writer.write("\"*\"}"+ END_LINE + NEW_LINE);
                        writer.write(" static int VENTANA_SECUENCIA"+name+" = 0"+ END_LINE + NEW_LINE);
                    }
                } else {
                    writer.write(" static " + dataType + SPACE + name + END_LINE + NEW_LINE);
                }
            }
        } catch (IndexOutOfBoundsException e){
            throw new AlgorithmException("Ambiente no encontrado");
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
        writer.write("import java.io.*; \n");
        writer.write("import com.frre.library.*; \n");
        writer.write("import static com.frre.library.archivos.FuncionesDeArchivos.*; \n");

        writer.write("public class Prueba { \n \n");
        writer.write("//ambiente \n \n");
    }

    public static void tryToExtractAction(String everything, Writer writer) throws IOException, AlgorithmException {
        try {
            String algoritmo = everything.substring(getPositionOf(ALGORITMO, everything) + ALGORITMO.length(), getPositionOf(FIN_ALGORITMO, everything));
            String[] actions = algoritmo.replaceAll(";", "").split("\n");
            int i = 0;
           try {
               writer.write("//algoritmo \n");
               writer.write("public static void main(String[] args){ \n");


               Stack<String> pilaAcciones = new Stack<>();
               while (i < actions.length) {
                   String line = actions[i].trim();
                   line = line.replace(" mod ", " % ");
                   //MyLogger.log(i + " " + line);
                   if (!line.startsWith("//") && line.length() > 0) {
                       if (!detectAction(line, writer, pilaAcciones, false) && !specialAction(line, writer, pilaAcciones)) {
                           MyLogger.log("Error compilando!");
                           System.out.println("Error en linea "+i+": "+line);
                           break;
                       }
                   } else {
                       MyLogger.log("Comentario encontrado");
                   }
                   i++;
               }

               if (pilaAcciones.size() > 0){
                   MyLogger.log("Error compilando!");
                   System.out.println("Error en algoritmo no se cerro "+pilaAcciones);
               }

               writer.write("}\n");
           } catch (Exception e){
               throw new AlgorithmException("Error encontrado en "+actions[i]+" \n Detalles:"+e.getMessage());
           }
        } catch (IndexOutOfBoundsException e){
            throw new AlgorithmException("Bloque algoritmo mal cerrado/mal abierto ");
        }


    }

    public static boolean specialAction(String line, Writer writer, Stack<String> pilaAcciones) throws IOException, AlgorithmException {
        if (line.contains(Constants.SI) && line.contains(ENTONCES) && !line.contains(SINO)) {
            String logic = line.substring(getPositionOf(Constants.SI, line) + Constants.SI.length(), getPositionOf(ENTONCES, line));
            logic = logic.replaceAll(" = ", " == ").replaceAll(" <> ", " != ");
            logic = replaceWithEquals(logic);
            writer.write("if (" + logic + "){\n");
            pilaAcciones.push(line);
            return true;
        } else if (line.contains(FIN)) {
            writer.write("}\n");
            String linetoUpperCase =line.toUpperCase();
            //MyLogger.log(linetoUpperCase);
            if (linetoUpperCase.contains(SI)){
                findLastAction(pilaAcciones, SI);
            } else if (linetoUpperCase.contains("MIENTRAS")){
                findLastAction(pilaAcciones, MIENTRAS);
            } else if (linetoUpperCase.contains("PARA")){
                findLastAction(pilaAcciones, PARA);
            } else if (linetoUpperCase.contains("SEGUN")){
                findLastAction(pilaAcciones, SEGUN);
            }
            return true;
        } else if (line.contains(REPETIR)) {
            writer.write("do { \n");
            pilaAcciones.push(line);
            return true;
        } else if (line.trim().equalsIgnoreCase(SINO)) {
            writer.write("} else { \n");
            return true;
        } else if (line.contains(SINO_SI)) {
            String logic = line.substring(getPositionOf(SINO_SI, line) + SINO_SI.length(), getPositionOf(ENTONCES, line));
            logic = logic.replaceAll(" = ", " == ").replaceAll(" <> ", " != ");
            logic = replaceWithEquals(logic);
            writer.write("} else if (" + logic + "){\n");
            return true;
        } else if (line.contains(HASTA) && line.contains(")")) {
            String logic = line.substring(getPositionOf(HASTA, line) + HASTA.length(), getPositionOf(")", line));
            logic = logic.replaceAll(" = ", " == ").replaceAll(" <> ", " != ");
            logic = replaceWithEquals(logic);
            writer.write(" } while (" + logic + ");\n");
            findLastAction(pilaAcciones, REPETIR);
            return true;
        } else if (line.contains(MIENTRAS) && line.contains(HACER)) {
            String logic = line.substring(getPositionOf(MIENTRAS, line) + MIENTRAS.length(), getPositionOf(HACER, line));
            logic = logic.replaceAll(" = ", " == ").replaceAll(" <> ", " != ");
            logic = replaceWithEquals(logic);
            writer.write("while (" + logic + "){\n");
            pilaAcciones.push(line);
            return true;
        } else if (line.contains(PARA) && line.contains(")hacer")) {
            String logic = line.substring(getPositionOf(HASTA, line) + HASTA.length(), getPositionOf(")", line));
            String[] actions = logic.split(",");
            String letter = actions[0].split("hasta")[0].split(":=")[0].trim();
            String init = actions[0].split("hasta")[0].split(":=")[1].trim();
            String tope = actions[0].split("hasta")[1].trim();
            String aumento = actions[1].substring(actions[1].indexOf(letter) + letter.length()).trim();
            if (aumento.contains("=")) {
                aumento = aumento.replace(":=", "=");
            }
            writer.write("for(" + letter + "=" + init + ";" + letter + " <= " + tope + ";" + letter + aumento + ") { \n");
            pilaAcciones.push(line);
            return true;
        } else if (line.contains(SEGUN) && line.contains(HACER)){
            String variable = line.substring(getPositionOf(SEGUN, line) + SEGUN.length(), getPositionOf(HACER, line));
            if (theVariables.get(variable.trim()) == null){
                throw  new AlgorithmException("Variable no existe para hacer el segun en el ambiente");
            } else {
                SEGUN_VARIABLE = variable;
                SEGUN_COUNTER = 1;
            }
            pilaAcciones.push(line);
            return true;
        } else if (pilaAcciones.peek().contains(SEGUN)){
            String[] actions = line.split(":");
            if (actions.length > 1){
                String logic = actions[0].trim();
                String action = actions[1].trim();
                if (logic.contains("=")&&!(logic.contains("<")||logic.contains(">"))){
                    logic = logic.replaceAll("=", " == ");
                }
                logic.replaceAll("<>", " != ");
                if (!logic.equalsIgnoreCase("otros")){
                    writer.write(SEGUN_COUNTER > 1?" else ":"");
                    writer.write("if ("+SEGUN_VARIABLE);
                    writer.write(logic+"){ \n");
                    detectAction(action, writer, pilaAcciones, true);
                    writer.write("}");
                    SEGUN_COUNTER++;
                } else {
                    writer.write(SEGUN_COUNTER > 1?" else { \n":"");
                    detectAction(action, writer, pilaAcciones, true);
                }
            } else {
                MyLogger.log("Pasare por aqui??? ");
                detectAction(line,writer, pilaAcciones, true);
            }
            return true;
        }
        return false;
    }

    private static void findLastAction(Stack<String> pilaAcciones, String method) {
        //MyLogger.log("Entrandoooo "+pilaAcciones+" --- "+method);
        for (int i = pilaAcciones.size()-1; i > -1; i--) {
            String action = pilaAcciones.get(i).toUpperCase();
            //MyLogger.log("Encontrado! pilaAcciones.get(i).toUpperCase()="+action+" "+action.contains(method.toUpperCase()));
            if (action.contains(method.toUpperCase())){
                pilaAcciones.remove(i);
                break;
            }
        }
        //MyLogger.log(pilaAcciones+" --- "+method);
    }

    public static String replaceWithEquals(String logic) {
        StringBuilder builder = new StringBuilder();
        if (logic.contains(" y ")) {
            String[] actions = logic.split((" y "));
            for (int i = 0; i < actions.length; i++) {
                String subline = actions[i];
                if (subline.contains(" o ")) {
                    String[] ires = subline.split(Pattern.quote(" o "));
                    for (int j = 0; j < ires.length; j++) {
                        String[] line = ires[j].split(" == ");
                        if (line.length > 1 && theVariables.get(line[0]) != null && theVariables.get(line[0]).trim().equalsIgnoreCase("String")) {
                            builder.append(line[0].trim() + ".equalsIgnoreCase(" + line[1].trim() + ")");
                        } else {
                            builder.append(ires[j]);
                        }
                        if (j != ires.length - 1) {
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
                if (i != actions.length - 1) {
                    builder.append(" && ");
                }
            }
        } else if (logic.contains(" | ")) {
            String[] actions = logic.split(Pattern.quote(" | "));
            for (int i = 0; i < actions.length; i++) {
                String subline = actions[i];
                String[] line = subline.split(" == ");
                if (line.length > 1 && theVariables.get(line[0]) != null && theVariables.get(line[0]).trim().equalsIgnoreCase("String")) {
                    builder.append(line[0].trim() + ".equalsIgnoreCase(" + line[1].trim() + ")");
                } else {
                    builder.append(subline);
                }
                if (i != actions.length - 1) {
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

    public static boolean detectAction(String line, Writer writer, Stack<String> pilaAcciones, boolean forceEnter) throws IOException, AlgorithmException {
        if (pilaAcciones.size() == 0 || pilaAcciones.size() > 0 && (!pilaAcciones.peek().contains(SEGUN)) || forceEnter){
            if (line.contains(ESCRIBIR) || line.contains(ESCRIBIR.toLowerCase())) {
                String titAMostrar = line.substring(getPositionOf(ESCRIBIR, line) + ESCRIBIR.length(), getPositionOf(")", line));
                if (sMapaSecuencias.get(titAMostrar.split(",")[0])!=null){
                    //tengo que escribir en la secuencia nueva
                    String character = titAMostrar.split(",")[1];
                    String seq = titAMostrar.split(",")[0];
                    writer.write(seq+"[VENTANA_SECUENCIA"+seq+"]="+character+"; \n");
                   // writer.write("System.out.print("+seq+"[VENTANA_SECUENCIA"+seq+"]); \n");
                    //writer.write("System.out.print("+character+"); \n");
                    writer.write("VENTANA_SECUENCIA"+seq+"++; \n");


                    return true;
                } else {
                    if (titAMostrar.contains(",\"") || titAMostrar.contains("\",")) {
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
                }
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
            }  else if (line.contains(ARRANCAR) || line.toLowerCase().contains(ARRANCAR.toLowerCase())) {
                String nombreSecuencia = line.substring(getPositionOf(ARRANCAR, line) + ARRANCAR.length(), getPositionOf(")", line));
                if (theVariables.get(nombreSecuencia) == null) {
                    throw new AlgorithmException("No es una secuencia validad para arrancar");
                } else {
                    sMapaSecuencias.put(nombreSecuencia, 1);
                    pilaAcciones.push(nombreSecuencia);
                }
                return true;
            } else if (line.contains(CREAR) || line.toLowerCase().contains(CREAR.toLowerCase())) {
                String nombreSecuencia = line.substring(getPositionOf(CREAR, line) + CREAR.length(), getPositionOf(")", line));
                if (theVariables.get(nombreSecuencia) == null) {
                    throw new AlgorithmException("No es una secuencia validad para crear");
                } else {
                    sMapaSecuencias.put(nombreSecuencia, 1);
                    pilaAcciones.push(nombreSecuencia);
                    secuenciasPorCrear.add(nombreSecuencia);
                }
                writer.write(" "+nombreSecuencia+" = new String[9000];\n");
                return true;
            } else if (line.contains(CERRAR) || line.toLowerCase().contains(CERRAR.toLowerCase())) {
                String nombreSecuencia = line.substring(getPositionOf(CERRAR, line) + CERRAR.length(), getPositionOf(")", line));
                if (theVariables.get(nombreSecuencia) == null) {
                    throw new AlgorithmException("No es una secuencia validad para arrancar");
                } else {
                    sMapaSecuencias.remove(nombreSecuencia);
                    pilaAcciones.remove(nombreSecuencia);
                    if (secuenciasPorCrear.contains(nombreSecuencia)){
                        secuenciasPorCrear.remove(nombreSecuencia);
                        MyLogger.log(nombreSecuencia);

                        writer.write("String output = \"\"; \n");
                        writer.write("for (int i = 0; i < "+nombreSecuencia+".length; i++) { \n");
                        writer.write(" if ("+nombreSecuencia+"[i] != null) { \n");
                        writer.write(" output += "+nombreSecuencia+"[i]; \n } \n");
                        writer.write("}\n");

                        String contents = " try{ \n" +
                                "String contents = output;\n" +
                                "File file = new File(\""+nombreSecuencia+"\");\n" +
                                "file.createNewFile();\n" +
                                "FileWriter fw = new FileWriter(file);\n" +
                                "BufferedWriter bw = new BufferedWriter(fw);\n" +
                                "bw.write(contents);\n" +
                                "bw.close();\n" +
                                "fw.close();\n" +
                                "} catch (Exception ex) {\n" +
                                "}\n";
                         writer.write(contents);
                    }
                }
                return true;
            } else if (line.contains(AVANZAR) || line.toLowerCase().contains(AVANZAR.toLowerCase())) {
                String secuenciaAndCharacter = line.substring(getPositionOf(AVANZAR, line) + AVANZAR.length(), getPositionOf(")", line));
                String[] vars = secuenciaAndCharacter.split(",");
                if (theVariables.get(vars[1]) == null || theVariables.get(vars[0]) == null) {
                    throw new AlgorithmException("Avanzar mal definido en "+line);
                } else if (sMapaSecuencias.get(vars[0].trim()) == null) {
                    throw new AlgorithmException("No se puede avanzar sin antes arrancar la secuencia "+line);
                } else {
                    String character = vars[1];
                    String seq = vars[0];
                    writer.write(character+"="+seq+"[VENTANA_SECUENCIA"+vars[0]+"]; \n");
                    writer.write("VENTANA_SECUENCIA"+vars[0]+"++; \n");
                }
                return true;
            } else if (line.contains(":=") && !line.contains(PARA)) {
                String[] assigns = line.split(":=");
                if (theVariables.get(assigns[0].trim()) != null){
                    writer.write(assigns[0].trim() + SPACE + "=" + SPACE + assigns[1].trim() + ";\n");
                } else {
                    throw  new AlgorithmException("Variable no encontrada en ambiente "+assigns[0].trim());
                }
                return true;
            }
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

    public static void analizeAndExec(String algoritmo, String secuenciaArchivo, boolean debugMode) throws IOException {
        FileInputStream inputStream = null;
        StringWriter writer = null;
        try {
            DEBUG_MODE = debugMode;
            inputStream = new FileInputStream(algoritmo);
            writer = new StringWriter();
            PrintWriter out = new PrintWriter(writer);
            String everything = IOUtils.toString(inputStream);

            File secArchivo = new File(secuenciaArchivo);
            String secuenciaArchivoContents = null;
            if (secArchivo.exists()){
                secuenciaArchivoContents = IOUtils.toString(new FileInputStream(secuenciaArchivo));
            }

            //public class
            addHeader(writer);
            // getting the variables
            tryToExtractVariables(everything, secuenciaArchivoContents, writer);
            //load file or character sequence
            //main
            tryToExtractAction(everything, writer);
            //final {
            addFooter(writer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            inputStream.close();
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
        compilationAndExec(writer);
    }

    private static void tryToInjectFileSecContents(String secuenciaArchivoContents, StringWriter writer) {

    }

    public static int findWord(String[] args, String word) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase(word)) {
                return i;
            }
        }
        return -1;
    }

    public static int countWordOcurrences(String[] args, String word) {
        int cont = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase(word)) {
                cont++;
            }
        }
        return cont;
    }

    public static String[] removeWord(String[] args, String word) {
        int cantOcurrencias = countWordOcurrences(args, word);
        String[] nuevoArray = new String[args.length - cantOcurrencias];
        int j = 0;
        for (int i = 0; i < args.length; i++) {
            if (!args[i].equalsIgnoreCase(word)) {
                nuevoArray[j] = args[i];
                j++;
            }
        }
        return nuevoArray;
    }
}

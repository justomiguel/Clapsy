Clapsy es una libreria espcialmente construida para reproducir los Algoritmos de la carrera de ISI de la UTN FRRe y probarlos en tiempo real, traduciendolos a JAVA y ejecutandolos!. 

## Como Usarla //Normal Mode

1. Deberan descargar el archivo jar desde aqui...

    [Archivo JAR](https://github.com/justomiguel/Clapsy/raw/master/Clapsy-1.0.jar)

2. Para ejecutarlo deberan abrir una terminal (linux) o el famoso simbolo del sistema (Windows) y con eso desplazarce hasta donde descargaron el jar y ejecutar lo siguiente:

```
$ java -jar Clapsy-1.0.jar NOMBRE_TU_ALGORITMO.algoritmo
```

Si bien no es una convencion pueden poner a los files que usan para el algoritmo como .algoritmo. La libreria tomara el archivo y lo tratara de ejecutar. Traten de seguir las convenciones de escritura que son bien similares o iguales a las de la catedra, explicada mas abajo.
 En la carpeta de algoritmos pueden encontrar algunos ejemplos para que puedan probarla de la forma explicada ayer..
 
```
 $ java -jar Clapsy-1.0.jar algoritmos/Prueba.algoritmo
```


## Como Usarla //For Pro Users

1. Para poder bajar el codigo fuente deberan de tener instalado GIT en su computadora... Para bajarse pueden descargarse GIT desd aqui...

    [Link de Descarga de GIT](https://git-scm.com/downloads)

2. Y luego realizar por la consola de comandos lo siguiente:

```
    git clone https://github.com/justomiguel/Clapsy.git
```

3. [Descarga de Maven](https://maven.apache.org/install.html) (Seguir los pasos de la pagina y despues por consola hacer), de esa forma les bajara las dependencias, esto es opcional ya que la ide trae maven por defecto pero seria bueno que lo hagan

```
    mvn clean install   
```

4. Pueden usar la libreria con una IDE de preferencia o bien usar INTELLIJ IDEA desde aqui! Bajense el community! Un abrazo!

    [Descarga de la IDE](https://www.jetbrains.com/idea/download/)
    
5. Abran el proyecto con la IDE y vayan hasta la clase Main que se encuentra en com/frre/practica/isi/algoritmos/genericos

    ![](http://i.imgur.com/1DwNCJX.jpg)
    
6. Para compilarlos y ejecutarlos solo deben hacer click derecho sobre el archivo y elegir la opcion de run y colocar sobre las opciones de ejecucion el nombre del archivo que quieran ejecutar. Y la magia sucede!!

## Documentacion

**Un Algoritmo basico** 

Todo algoritmo debe tener una estructura similar a la siguiente de forma que el analizador la tome...

```
Accion AlgunNombre es;

    Ambiente
        //Variables aqui

    Algoritmo

        //Cuerpo del algoritmo aqui

    Fin_Algoritmo

Fin_Accion;
```

**Definiendo Variables y Solicitando datos al usuario** 

```
Accion Prueba es;

    Ambiente
        nombre: AlfaNumerico;
        apellido: AlfaNumerico;

    Algoritmo

        Escribir("Ingrese nombre");
        Leer(nombre);
        Escribir("Ingrese apellido");
        Leer(apellido);
        Escribir("Programando like a champ ",apellido," ",nombre);
        
    Fin_Algoritmo

Fin_Accion;
```

* Como veran para escribir por pantalla y separar de variables o de cadenas explicitas deben usar la "," como lo hacemos en clase.


**Usando setencias condicionales** 

* Pueden usar el condicional simple usando ^ o | para concatenar expresiones mas complejas

```
Si(apellido = "Vargas" ^ nombre = "Migue")entonces;
    Escribir("Grande ",apellido," ",nombre);
Fin_si
```

* O bien usar el condicional alternativo

```
Si(apellido = "Vargas" ^ nombre = "Migue")entonces;
    Escribir("Grande ",apellido," ",nombre);
sino
    Escribir("No esta bien!")
Fin_si
```

* O bien el anidado

```
Si(apellido = "Vargas" ^ nombre = "Migue")entonces;
     Escribir("Grande ",apellido," ",nombre);
sino si(apellido = "Bedoya")entonces
     Escribir("Lobo: ",apellido," ",nombre);
sino
     Escribir("No esta bien!")
Fin_si
```

* O bien el multiple

```
        Segun(suma)hacer

            <50: Escribir("La suma es menor 50.")
            =50: Escribir("La suma es igual a 50.")
            <=100: Escribir("La suma es mayor a 50 y menor o igual a 100.")
            <=200: Escribir("La suma es mayor a 100 y menor o igual a 200.")
            Otros: Escribir("La suma es mayor a 200.")

        Fin_segun
```


**Usando setencias repetitivas** 

* Manejada por contador o el para

```
Para(i:=0 hasta 50, i:=i+2)hacer
   numero := numero +1;
   Escribir(numero)
   Si(numero mod 6 = 0)entonces
     Escribir("****")
   Fin_si
Fin_para
```

* Post Test o el repetir

```
Repetir
   numero := numero +1;
   Si(numero mod 2 = 0)entonces
      Escribir(numero)
   Fin_si
Hasta(numero < 100);
```

* Pre Test o el mientras

```
Mientras(i < 100)hacer
  i := i +1;
  Escribir(i)
  Si(i mod 6 = 0)entonces
    Escribir("****")
  Fin_si
Fin_mientras
```

**Poniendonos pro!** 

* Hacer un algoritmo que imprima los numeros primos del 1 al 100

```
Accion Prueba es;

    Ambiente
        cont:entero;
        i:entero;
        j:entero;

    Algoritmo

        Escribir("Escribir todos los numeros primos del 1 al 100");
        numero := 0;
        i := 2;
        //escribo el 1 por que es primo comienzo desde el 2 sino la logica del condicional no me sirve
        Escribir(1);
        Mientras(i < 100)hacer
            cont := 0;
            Para(j=1 hasta i, j++)hacer
                Si(i mod j = 0)entonces
                    cont := cont + 1;
                Fin_si
            Fin_para
            Si(cont = 2)entonces
                Escribir(i);
            Fin_si
            i := i +1;
        Fin_mientras

    Fin_Algoritmo

Fin_Accion;
```

En la medida que pueda agregare mas cosas. Estaria genial que vayan probando la libreria y me digan si con algun ejercicio no les anduvo y me lo pasen!

## License

    Copyright 2016 Justo Vargas
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
Clapsy es una libreria espcialmente construida para reproducir los Algoritmos de la carrera de ISI de la UTN FRRe y probarlos en tiempo real, traduciendolos a JAVA y ejecutandolos!. 

## Como Usarla Normal Mode

1. Deberan descargar el archivo jar desde aqui...

    [Archivo JAR](https://github.com/justomiguel/Clapsy/raw/master/Clapsy-1.0.jar)

2. Para ejecutarlo deberan abrir una terminal (linux) o el famoso simbolo del sistema (Windows) y con eso desplazarce hasta donde descargaron el jar y ejecutar lo siguiente:

```
$ java -jar Clapsy-1.0.jar NOMBRE_TU_ALGORITMO.algoritmo
```

Si bien no es una convencion pueden poner a los files que usan para el algoritmo como .algoritmo. La libreria tomara el archivo y lo tratara de ejecutar. 

## Como Usarla For Pro Users

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
    
6. Alli veran que la linea esta por ejecutar el algoritmo llamado Prueba3 que esta en la carpeta algoritmos. Por ahora solo funciona con estructuras repetitivas y condicionales. Prueben haciendo sus propios algoritmos! Para compilarlos y ejecutarlos solo deben hacer click derecho sobre el archivo y elegir la opcion de run. Y la magia sucede!!

## Un Algoritmo basico

Todo algoritmo debe tener una estructura similar a la siguiente de forma que el analizador la tome...

```JAVA
if (isAwesome){
  return true
}
```

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
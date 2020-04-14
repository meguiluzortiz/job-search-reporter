# Jobs Search Reporter

Un pequeño CLI para buscar trabajo con Github basado en el proyecto realizado en el [Curso de Programación Funcional con Java SE](https://platzi.com/clases/java-funcional/).

---

## Descripcion

Nuestro pequeño CLI utilizara [la API de Github](https://jobs.github.com/) para buscar empleos.
Para que nuestro CLI pueda funcionar debemos pasar algunas opciones/parametros de busqueda.

La manera de invocar a nuestro CLI sera:

```bash
#!/bin/bash
jobs-search [OPTIONS] <SKILL>
```

Donde `[OPTIONS]` son:

```bash
#!/bin/bash
--location <UBICACION>  # <UBICACION>: Algun lugar del mundo, tambien disponible como: -l
--page <PAGINA>         # <PAGINA>: Los resultados se muestran de 50 en 50, cada 50 resultados se le conoce como pagina empezando en 0. Default: 0. Tambien disponible como: -p
--full-time             # Si queremos que sean solo empleos de tiempo completo. default: false
--markdown              # Mostrar los resultados en Markdown
```

Y `<SKILL>` es el tipo de skill del que queremos encontrar trabajos.

### Ejemplo

Para buscar trabajos de `Java` en Tokyo, usariamos:

`jobs-search --location tokyo java`

### Por hacer

- [ ] Incluir cobertura al 50%
- [ ] Incluir plugin SpotBugs
- [ ] Incluir plugin Prettier
- [ ] Incluir plugin Sonar
- [ ] Agregar nuevas funcionalidades

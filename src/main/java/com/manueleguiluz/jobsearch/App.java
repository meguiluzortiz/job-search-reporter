package com.manueleguiluz.jobsearch;

import static com.manueleguiluz.jobsearch.CommanderFunctions.buildCommanderWithName;
import static com.manueleguiluz.jobsearch.CommanderFunctions.parseArguments;
import static com.manueleguiluz.jobsearch.api.ApiFunctions.buildApi;

import com.beust.jcommander.JCommander;
import com.manueleguiluz.jobsearch.api.ApiJobs;
import com.manueleguiluz.jobsearch.cli.CliArguments;
import com.manueleguiluz.jobsearch.cli.CliFunctions;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class App {

  /**
   * Inicia el programa.
   *
   * @param args argumentos de aplicaci&oacute;n.
   */
  public static void main(final String[] args) {
    // Creacion de nuestro CLI con JCommander
    final JCommander jCommander = buildCommanderWithName("job-search", CliArguments::newInstance);

    // Obtenemos las opciones que se le dieron a JCommander
    final Stream<CliArguments> streamOfCli =
        // Nos retorna un Optional<List<Object>>
        parseArguments(jCommander, args, JCommander::usage)
            // En caso de un Optional.empty()
            .orElse(Collections.emptyList()).stream().map(obj -> (CliArguments) obj);

    // Tomamos nuestro Stream y obtenemos las opciones que se dieron en el CLI
    final Optional<CliArguments> cliOptional = streamOfCli
        // Solo nos interesan los casos donde no sea la solicitud de ayuda
        .filter(cli -> !cli.isHelp())
        // Y que contengan un keyword, en otros caso no tenemos que buscar
        .filter(cli -> cli.getKeyword() != null).findFirst();

    // Si el Optional tiene un dato, lo convertimos a Map<String,Object>
    cliOptional.map(CliFunctions::toMap)
        // Convertimos el Map en un request
        .map(App::executeRequest)
        // Aun seguimos operando sobre un Optional… en caso de que no hubiera datos
        // Generamos un stream vacio
        .orElse(Stream.empty())
        // Imprimos los datos por pantalla.
        .forEach(System.out::println);
  }

  private static Stream<JobPosition> executeRequest(final Map<String, Object> options) {
    final ApiJobs api = buildApi(ApiJobs.class, "https://jobs.github.com");

    return Stream.of(options).map(api::jobs).flatMap(Collection::stream);
  }
}

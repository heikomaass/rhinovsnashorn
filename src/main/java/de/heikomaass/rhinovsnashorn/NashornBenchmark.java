package de.heikomaass.rhinovsnashorn;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by hmaass on 04.01.16.
 */
public class NashornBenchmark {

    public static void main(String[] args) throws ScriptException, IOException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");

        addScript("/base.js", engine);
        addScript("/richards.js", engine);
        addScript("/deltablue.js", engine);
        addScript("/crypto.js", engine);
        addScript("/raytrace.js", engine);
        addScript("/earley-boyer.js", engine);
        addScript("/regexp.js", engine);
        addScript("/splay.js", engine);
        addScript("/navier-stokes.js", engine);
        addScript("/pdfjs.js", engine);
        addScript("/mandreel.js", engine);
        addScript("/gbemu-part1.js", engine);
        addScript("/gbemu-part2.js", engine);
        addScript("/code-load.js", engine);
        addScript("/box2d.js", engine);
//        addScript("/zlib.js", engine);
//        addScript("/zlib-data.js", engine);
        addScript("/typescript.js", engine);
        addScript("/typescript-input.js", engine);
        addScript("/typescript-compiler.js", engine);

        engine.eval("BenchmarkSuite.RunSuites({\n" +
                "      NotifyStart : Packages.de.heikomaass.rhinovsnashorn.BenchmarkCallbacks.showBox,\n" +
                "      NotifyError : Packages.de.heikomaass.rhinovsnashorn.BenchmarkCallbacks.addError,\n" +
                "      NotifyResult : Packages.de.heikomaass.rhinovsnashorn.BenchmarkCallbacks.addResult,\n" +
                "      NotifyScore : Packages.de.heikomaass.rhinovsnashorn.BenchmarkCallbacks.addScore\n" +
                "    },\n" +
                "    []);");
    }

    private static void addScript(String path, ScriptEngine engine) throws ScriptException, IOException {
        try (Reader reader = Utils.fromResource(path)) {
            engine.eval(reader);
        }
    }

}

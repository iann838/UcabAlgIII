module Alg3Taller6Grupo7 {
    requires transitive com.google.gson;
    requires java.desktop;

    exports edu.ucab.taller6.serializers;
    exports edu.ucab.taller6.models;

    opens edu.ucab.taller6.serializers;
    opens edu.ucab.taller6.models;
}
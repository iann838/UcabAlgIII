module Alg3Taller5Grupo8 {
    requires transitive com.google.gson;
    exports edu.ucab.taller5.serializers;
    exports edu.ucab.taller5.models;
    opens edu.ucab.taller5.serializers;
    opens edu.ucab.taller5.models;
}
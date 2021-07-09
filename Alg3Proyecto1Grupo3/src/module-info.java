module Alg3Proyecto1Grupo3 {
	requires com.google.gson;
	requires org.apache.httpcomponents.httpclient;
	requires org.apache.httpcomponents.httpcore;
	requires java.desktop;
	exports edu.ucab.cryptomonitor.models;
	exports edu.ucab.cryptomonitor.serializers;
	exports com.paaksing.jjango;
	opens edu.ucab.cryptomonitor.models;
	opens edu.ucab.cryptomonitor.serializers;
	opens com.paaksing.jjango;
}

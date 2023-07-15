package hooks;

import io.cucumber.java.Before;

import static base_urls.MedunnaBaseUrl.setUp;


public class Hooks {
    @Before()// junitten(test annotationundan önce calisir) almayacagiz before annnotationunu cucumberdan alacagiz
    // her senaryodan önce calismasi icin bos birakirsak metod icini böylece her senaryodan önce calisacak.
    // istersek parantez icine tagleri yazip sadece o tagden önce calis diyebilirim
   public void beforeapi(){
        setUp();
    }
}

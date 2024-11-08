// https://www.baeldung.com/groovy-spock
import spock.lang.Specification

class FirstSpecification extends Specification {
  
  def "two plus two should equal four p"() {
    given:
        println "Ejecutando la prueba en FirstSpecification"
        int left = 2
        int right = 2

    when:
        int result = left + right

    then:
        result == 4
}
}
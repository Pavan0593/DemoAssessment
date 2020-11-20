export WEB_DRIVER_TYPE="webdriver.chrome.driver"
export WEB_DRIVER_PATH="/home/administrator/Downloads/chromedriver_linux64/chromedriver"
export USER1="poleary"
export PASSWORD1="ac0n3x72"
export USER2="nichol.kilback"
export PASSWORD2="iuy837lq2bi3a1"

mvn clean install -DskipTests
mvn test -Dtest=TestSampleAssesmentTest 

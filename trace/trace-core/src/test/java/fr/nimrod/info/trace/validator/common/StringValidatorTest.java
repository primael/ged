package fr.nimrod.info.trace.validator.common;

import org.junit.Assert;
import org.junit.Test;

public class StringValidatorTest {

	private StringValidator validator = new StringValidator();
	
	@Test
	public void StringValidatorSimpleTest(){
		Assert.assertEquals("chaine simple", "\"primael\"", validator.validateAndTransform("primael"));
		Assert.assertEquals("chaine simple longue", "\"primaelbruant\"", validator.validateAndTransform("primaelbruant"));
		Assert.assertEquals("chaine simple avec chiffre", "\"primael12bruant\"", validator.validateAndTransform("primael12bruant"));
		Assert.assertEquals("chaine simple avec accent", "<UHJpbWHDq2wgQnJ1YW50>",validator.validateAndTransform("Primaël Bruant"));
		Assert.assertEquals("chaine simple avec delimiteur", "<PD4icHJpbWFlbCI=>", validator.validateAndTransform("<>\"primael\""));
	}
	
	@Test
	public void StringValidatorWithSpaceTest(){
		Assert.assertEquals("chaine simple avec espace", "\" primael bruant\"", validator.validateAndTransform(" primael bruant"));
		Assert.assertEquals("chaine simple avec tabulation", "\"	primael	bruant\"", validator.validateAndTransform("	primael	bruant"));
		Assert.assertEquals("chaine simple avec espaces", "\"    primael		bruant  \"", validator.validateAndTransform("    primael		bruant  "));
	}
	
	@Test
	public void StringValidatorWithPunctuationTest(){
		Assert.assertEquals("chaine simple avec ponctuation", "\"primael,bruant\"", validator.validateAndTransform("primael,bruant"));
		Assert.assertEquals("chaine simple avec ponctuations", "\"Primael, bruant!\"", validator.validateAndTransform("Primael, bruant!"));
	}
	
	@Test
	public void StringValidatorTextTest(){
		String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce laoreet fringilla ex, eget ultrices arcu dapibus vel. Cras sed neque iaculis, tincidunt enim ac, suscipit augue. Morbi dapibus suscipit sodales. Aliquam vulputate posuere urna vel tristique. Phasellus vehicula fringilla leo, eget faucibus nisl pharetra nec. Aenean feugiat quis dui vitae volutpat. Aenean rutrum, lectus in maximus rhoncus, nulla ligula condimentum libero, eu volutpat lacus justo in turpis.\n";
		loremIpsum += "Nam eu tincidunt orci. Quisque quis risus rutrum, sagittis odio sed, euismod velit. Nullam vestibulum ante mauris, id congue magna gravida ut. Curabitur congue venenatis commodo. Nam pharetra magna et ante euismod finibus. Nam sagittis malesuada sapien vitae hendrerit. Duis nibh est, porta sit amet sapien ac, consequat euismod risus. Nulla ultricies felis ullamcorper velit facilisis gravida. Praesent aliquam feugiat purus, nec accumsan mauris sodales quis.\n";
		loremIpsum += "Nam nec blandit tellus. Maecenas et ipsum quis libero maximus molestie. Integer id neque a sem sagittis eleifend eu a orci. Mauris sit amet ipsum orci. Sed tincidunt faucibus nunc ut imperdiet. Morbi mollis nulla in mi viverra, vitae ornare est blandit. Curabitur facilisis nulla nibh, dictum semper odio ullamcorper non. Phasellus consequat sem id placerat consectetur. Proin posuere nunc quis magna porta, sit amet tempor leo laoreet. Pellentesque sit amet arcu eget tellus vehicula feugiat. Aenean eu iaculis risus. Phasellus non dui dolor. Aenean id rutrum sapien. Maecenas imperdiet blandit enim id pretium.\n";
		loremIpsum += "Praesent dapibus lacus ut mattis sagittis. Donec mi nibh, ultricies ac libero at, gravida fermentum dui. Proin egestas in magna in commodo. Nunc pretium, purus quis pharetra finibus, dolor tellus ultrices elit, eu volutpat erat enim vel ipsum. Aenean molestie ligula sed lorem varius, in volutpat elit egestas. Suspendisse venenatis, enim ut venenatis vulputate, lectus nisi scelerisque nibh, et tristique metus sapien ut ipsum. Sed eget rutrum nisi.\n";
		loremIpsum += "Aliquam id venenatis magna. Sed luctus neque sit amet tortor consectetur fringilla. Cras a congue nibh. Fusce purus est, tempus in viverra id, lacinia sed libero. Pellentesque metus turpis, venenatis eu diam pharetra, mollis vestibulum dolor. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam magna ipsum, lacinia in ultrices a, auctor eu lorem. Phasellus pulvinar ligula in lorem aliquet imperdiet. Donec eros velit, tincidunt a nulla id, scelerisque euismod libero. Proin sodales risus sit amet mi rhoncus rutrum. Maecenas viverra ex et erat porttitor cursus ut id velit. Aliquam sit amet justo id augue aliquam imperdiet. Aenean sodales vitae massa eu porta. Mauris sagittis sed tellus convallis vehicula. Integer id ultrices nisi.";
		
		Assert.assertEquals("texte simple","\"" + loremIpsum + "\"", validator.validateAndTransform(loremIpsum));
	}
}

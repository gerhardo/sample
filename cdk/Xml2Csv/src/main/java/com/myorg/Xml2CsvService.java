package com.myorg;

import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import software.amazon.awscdk.services.apigateway.LambdaIntegration;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

public class Xml2CsvService extends Construct {

	@SuppressWarnings("serial")
	public Xml2CsvService(@NotNull Construct scope, @NotNull String id) {
		super(scope, id);
		
        Function handler = Function.Builder.create(this, "Xml2CsvHandler")
                .runtime(Runtime.JAVA_11)
                .code(Code.fromAsset("target"))
                .handler("xml2csv.main")
                .environment(new HashMap<String, String>() {{
                }}).build();

        RestApi api = RestApi.Builder.create(this, "Xml2Csv-API")
                .restApiName("Widget Service").description("This API converts xml to csv.")
                .build();

        LambdaIntegration getXml2CsvIntegration = LambdaIntegration.Builder.create(handler) 
                .requestTemplates(new HashMap<String, String>() {{
                    put("application/json", "{ \"statusCode\": \"200\" }");
                }}).build();

        api.getRoot().addMethod("GET", getXml2CsvIntegration);    

	}



}

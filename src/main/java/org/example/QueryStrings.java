package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//operand1=11&operator=*&operand2=55
public class QueryStrings {
    private List<QueryString> queryStrings = new ArrayList<>();

    public QueryStrings(String queryStringLine) {
        String[] queryStringTokens = queryStringLine.split("&");
        System.out.println(queryStringTokens + "쿼리스트링토큰스입니다.");
        Arrays.stream(queryStringTokens)
                .forEach(queryString -> {
                    String[] values = queryString.split("=");
                    if (values.length != 2){
                        throw new IllegalArgumentException("잘못된 QueryString 포맷을 가진 문자열 입니다.");
                    }
                    queryStrings.add(new QueryString(values[0], values[1]));
                });

    }

    public String getValue(String key) {
        return this.queryStrings.stream()
                .filter(queryString -> queryString.exists(key))
                .map(QueryString::getValue)
                .findFirst()
                .orElse(null);
    }
}

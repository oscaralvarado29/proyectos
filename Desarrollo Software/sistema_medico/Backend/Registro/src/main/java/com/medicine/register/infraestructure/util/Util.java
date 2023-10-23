package com.medicine.register.infraestructure.util;

import graphql.language.SourceLocation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Util {
    public static SourceLocation toSourceLocation(Throwable t) {
        if (t.getStackTrace().length == 0) {
            return new SourceLocation(0,0,Constants.NOT_FOUND);
        }
        StackTraceElement st = t.getStackTrace()[0];
        return new SourceLocation(st.getLineNumber(), -1, st.toString());
    }
}

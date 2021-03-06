package com.sdc.languages.general.languageParts;

import com.sdc.ast.expressions.Expression;

import java.util.HashSet;
import java.util.Set;

public abstract class ClassField {
    protected final String myModifier;
    protected final String myType;
    protected final String myName;

    protected Expression myInitializer;
    protected Set<Method> myConstructors = new HashSet<Method>();

    protected final int myTextWidth;
    protected final int myNestSize;

    public ClassField(final String modifier, final String type, final String name, final int textWidth, final int nestSize) {
        this.myModifier = modifier;
        this.myType = type;
        this.myName = name;
        this.myTextWidth = textWidth;
        this.myNestSize = nestSize;
    }

    public String getModifier() {
        return myModifier;
    }

    public String getType() {
        return myType;
    }

    public String getName() {
        return myName;
    }

    public int getNestSize() {
        return myNestSize;
    }

    public void setInitializer(final Expression initializer) {
        this.myInitializer = initializer;
    }

    public Expression getInitializer() {
        return myInitializer;
    }

    public boolean hasInitializer() {
        return myInitializer != null;
    }

    public void addConstructor(Method method){
        myConstructors.add(method);
    }

    public boolean hasInitializer(Method method){
        return myConstructors.contains(method);
    }
}

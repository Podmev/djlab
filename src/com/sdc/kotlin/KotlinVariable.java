package com.sdc.kotlin;

import com.sdc.ast.expressions.Constant;
import com.sdc.ast.expressions.Expression;
import com.sdc.ast.expressions.identifiers.Variable;
import com.sdc.util.DeclarationWorker;

public class KotlinVariable extends Variable {
    public static final String SHARED_VAR_IDENTIFIER = "SharedVar.";

    private boolean myIsNotNull = false;
    private boolean myIsInForDeclaration = false;
    private int myAssignmentsCount = 0;

    public void setIsInForDeclaration(final boolean isInForDeclaration) {
        this.myIsInForDeclaration = isInForDeclaration;
    }

    public void addAssignment() {
        myAssignmentsCount++;
    }

    public static boolean isSharedVar(final String type) {
        return type.startsWith(SHARED_VAR_IDENTIFIER);
    }

    public KotlinVariable(final int index, final String variableType, final String name) {
        super(index, variableType, name);
    }

    public boolean isNotNull() {
        return myIsNotNull;
    }

    public void setIsNotNull(final boolean isNotNull) {
        this.myIsNotNull = isNotNull;
    }

    @Override
    public Expression getName() {
        final String name = ((Constant) super.getName()).getValue().toString();
        final String actualName = myIsMethodParameter || myIsInForDeclaration  ? name : (isMutable() ? "var " : "val ") + name;

        return myIsDeclared ? myName : new Constant(actualName, false);
    }

    @Override
    public String getType() {
        String actualType = super.getType();
        boolean notNeedNullableMark = myIsNotNull || DeclarationWorker.isPrimitiveClass(myVariableType) || myVariableType.endsWith("?");

        if (isSharedVar(myVariableType)) {
            actualType = DeclarationWorker.convertJavaPrimitiveClassToKotlin(actualType.substring(SHARED_VAR_IDENTIFIER.length()));
            if (!actualType.equals("Any")) {
                notNeedNullableMark = true;
            }
        }

        final String nullableMark = notNeedNullableMark ? "" : "?";

        return actualType + nullableMark;
    }

    public String getActualType() {
        return super.getType();
    }

    @Override
    protected Variable createVariable(final int index, final String variableType, final String name) {
        return new KotlinVariable(index, variableType, name);
    }

    public boolean isMutable() {
        boolean parentIsMutable = myParentCopy != null && ((KotlinVariable) myParentCopy).isMutable();
        boolean childIsMutable = myChildCopy != null && ((KotlinVariable) myChildCopy).isMutable();

        return isSharedVar(myVariableType) || parentIsMutable || childIsMutable || !myIsMethodParameter && myAssignmentsCount > 1;
    }
}

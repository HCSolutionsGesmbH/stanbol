/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.stanbol.rules.adapters.clerezza.atoms;

import java.util.ArrayList;
import java.util.List;

import org.apache.clerezza.rdf.core.UriRef;
import org.apache.clerezza.rdf.core.sparql.query.ConstructQuery;
import org.apache.clerezza.rdf.core.sparql.query.Expression;
import org.apache.clerezza.rdf.core.sparql.query.FunctionCall;
import org.apache.stanbol.rules.adapters.AbstractAdaptableAtom;
import org.apache.stanbol.rules.adapters.clerezza.ClerezzaSparqlObject;
import org.apache.stanbol.rules.base.api.RuleAtom;
import org.apache.stanbol.rules.base.api.RuleAtomCallExeption;
import org.apache.stanbol.rules.base.api.UnavailableRuleObjectException;
import org.apache.stanbol.rules.base.api.UnsupportedTypeForExportException;
import org.apache.stanbol.rules.manager.atoms.StringFunctionAtom;

/**
 * It adapts any UpperCaseAtom to the XPath FuncionCall <http://www.w3.org/2005/xpath-functions#upper-case> in
 * Clerezza.
 * 
 * @author anuzzolese
 * 
 */
public class UpperCaseAtom extends AbstractAdaptableAtom {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T adapt(RuleAtom ruleAtom) throws RuleAtomCallExeption,
                                         UnavailableRuleObjectException,
                                         UnsupportedTypeForExportException {

        org.apache.stanbol.rules.manager.atoms.UpperCaseAtom tmp = (org.apache.stanbol.rules.manager.atoms.UpperCaseAtom) ruleAtom;

        StringFunctionAtom argument = tmp.getStringFunctionAtom();

        ClerezzaSparqlObject argument1 = (ClerezzaSparqlObject) adapter.adaptTo(argument,
            ConstructQuery.class);

        List<Expression> argumentExpressions = new ArrayList<Expression>();
        argumentExpressions.add((Expression) argument1.getClerezzaObject());

        FunctionCall functionCall = new FunctionCall(new UriRef(
                "<http://www.w3.org/2005/xpath-functions#upper-case>"), argumentExpressions);

        return (T) new ClerezzaSparqlObject(functionCall);

    }

}

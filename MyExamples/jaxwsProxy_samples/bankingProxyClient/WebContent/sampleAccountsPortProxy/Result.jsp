<%@page contentType="text/html;charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
com.ibm.ccl.ws.jaxws.gstc.util.OutputUtils.init(session);

boolean async       = session.getAttribute("__async__") == null ? false : true;
String methodKey    = request.getParameter("key");
String resultSuffix = methodKey != null && methodKey.length() > 0 ? " - " + methodKey : "";
%>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
<LINK rel="stylesheet" type="text/css" href="TestClient.css"/>
<script language="JavaScript">

function reloadMethods() {
    window.parent.frames["methods"].location.reload(true);
}
</script>
</HEAD>
<BODY>
<TABLE class="heading" width="100%">
<TR><TD>Result<%= resultSuffix %></TD></TR>
</TABLE>
<P>
<jsp:useBean id="sampleAccountsPortProxyid" scope="session" class="com.example.services.banking.AccountsPortProxy" />

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

boolean isDone = true;
try {
    String sourceInTemp = request.getParameter("__rawxml__");
        javax.xml.transform.Source sourceIn  = sourceInTemp != null ?
            new javax.xml.transform.stream.StreamSource(new java.io.ByteArrayInputStream(sourceInTemp.getBytes())) : null;
    javax.xml.transform.Source sourceOut = null;

    boolean bypass = (sourceIn != null);

switch (methodID){ 
case 2:
    gotMethod = true;
    com.example.services.banking.AccountsPortProxy.Descriptor returnp3mtemp = null;
    if (methodKey != null) {
        javax.xml.ws.Response resp = com.ibm.ccl.ws.jaxws.gstc.util.AsyncUtils.getResponse(session, methodKey);
        if (resp != null) {
            isDone = resp.isDone();
            if (!isDone)
                break;
            if (resp.get() != null) {
                Object o = resp.get();
                if (o instanceof javax.xml.transform.Source)
                    sourceOut = (javax.xml.transform.Source) o;
                else if (o instanceof com.example.services.banking.AccountsPortProxy.Descriptor)
                    returnp3mtemp = (com.example.services.banking.AccountsPortProxy.Descriptor) o;
            }
        }
    }
    else if (bypass) {
        javax.xml.ws.Dispatch dispatch = sampleAccountsPortProxyid._getDescriptor().getDispatch();

        if (request.getParameter("__use_soapaction__") != null)
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.useSoapAction(dispatch, request.getParameter("__soapaction__"));
        else
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.clearSoapAction(dispatch);

        if (!async)
            sourceOut = (javax.xml.transform.Source) dispatch.invoke(sourceIn);
        else {
            // async code omitted
            break;
        }
    } else {
        if (!async) {
        try {
            returnp3mtemp = sampleAccountsPortProxyid._getDescriptor();
            }catch(Exception exc){
                %>
                Exception: <%= exc %>
                Message: <%= exc.getMessage() %>
                <%
break;
            }
        }
        else {
            // async code omitted
            break;
        }
    }
if (sourceOut != null) {
%>
    <TEXTAREA ROWS="8" COLs="45"><%= org.eclipse.jst.ws.util.JspUtils.markup(com.ibm.ccl.ws.jaxws.gstc.util.SourceUtils.transform(sourceOut)) %></TEXTAREA>
<%
}
else {
if (returnp3mtemp == null) {
%>
    null
<%
} else {
    %>
    <TABLE CLASS="tableform">
<TR>
<TD COLSPAN="2" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">returnp:</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="1" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">proxy:</TD>
<TD>
<%
if(returnp3mtemp != null){
com.example.services.banking.BankingSEI typeproxy5 = returnp3mtemp.getProxy();
if(typeproxy5 != null){
        if(typeproxy5!= null){
        String tempproxy5 = typeproxy5.toString();
        %>
        <%=tempproxy5%>
        <%
        }}
else{
        %>
        <%= typeproxy5%>
        <%
}
}
%>
</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="1" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">dispatch:</TD>
<TD>
<%
if(returnp3mtemp != null){
javax.xml.ws.Dispatch typedispatch7 = returnp3mtemp.getDispatch();
if(typedispatch7 != null){
        if(typedispatch7!= null){
        String tempdispatch7 = typedispatch7.toString();
        %>
        <%=tempdispatch7%>
        <%
        }}
else{
        %>
        <%= typedispatch7%>
        <%
}
}
%>
</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="1" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">endpoint:</TD>
<TD>
<%
if(returnp3mtemp != null){
java.lang.String typeendpoint9 = returnp3mtemp.getEndpoint();
if(typeendpoint9 != null){
        String tempResultendpoint9 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeendpoint9));
        %>
        <%= tempResultendpoint9 %>
        <%
}
else{
        %>
        <%= typeendpoint9%>
        <%
}
}
%>
</TD>
    </TABLE>
    <%
}
%>
<HR/><BR/>
<%
}
break;
case 11:
    gotMethod = true;
    long returnp12mtemp = 0;
    if (methodKey != null) {
        javax.xml.ws.Response resp = com.ibm.ccl.ws.jaxws.gstc.util.AsyncUtils.getResponse(session, methodKey);
        if (resp != null) {
            isDone = resp.isDone();
            if (!isDone)
                break;
            if (resp.get() != null) {
                Object o = resp.get();
                if (o instanceof javax.xml.transform.Source)
                    sourceOut = (javax.xml.transform.Source) o;
                else if (o instanceof java.lang.Long)
                    returnp12mtemp = ((java.lang.Long) o).longValue();
            }
        }
    }
    else if (bypass) {
        javax.xml.ws.Dispatch dispatch = sampleAccountsPortProxyid._getDescriptor().getDispatch();

        if (request.getParameter("__use_soapaction__") != null)
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.useSoapAction(dispatch, request.getParameter("__soapaction__"));
        else
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.clearSoapAction(dispatch);

        if (!async)
            sourceOut = (javax.xml.transform.Source) dispatch.invoke(sourceIn);
        else {
            // async code omitted
            break;
        }
    } else {
        String owner_0id=  request.getParameter("owner14");
        String owner14null = request.getParameter("owner14null");
        java.lang.String owner_0idTemp;
        if (owner14null != null)
            owner_0idTemp = null;
        else {
         owner_0idTemp  = owner_0id;
        }

        String initialBalance_2id=  request.getParameter("initialBalance16");
        double initialBalance_2idTemp  = Double.parseDouble(initialBalance_2id);

        if (!async) {
        try {
            returnp12mtemp = sampleAccountsPortProxyid.createAccount(owner_0idTemp,initialBalance_2idTemp);
            }catch(Exception exc){
                %>
                Exception: <%= exc %>
                Message: <%= exc.getMessage() %>
                <%
break;
            }
        }
        else {
            // async code omitted
            break;
        }
    }
if (sourceOut != null) {
%>
    <TEXTAREA ROWS="8" COLs="45"><%= org.eclipse.jst.ws.util.JspUtils.markup(com.ibm.ccl.ws.jaxws.gstc.util.SourceUtils.transform(sourceOut)) %></TEXTAREA>
<%
}
else {
        String tempResultreturnp12 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(returnp12mtemp));
        %>
        <%= tempResultreturnp12 %>
        <%
%>
<HR/><BR/>
<%
}
break;
case 18:
    gotMethod = true;
    java.lang.Double amount23mtemp = null;
    if (methodKey != null) {
        javax.xml.ws.Response resp = com.ibm.ccl.ws.jaxws.gstc.util.AsyncUtils.getResponse(session, methodKey);
        if (resp != null) {
            isDone = resp.isDone();
            if (!isDone)
                break;
            if (resp.get() != null) {
                Object o = resp.get();
                if (o instanceof javax.xml.transform.Source)
                    sourceOut = (javax.xml.transform.Source) o;
            }
        }
    }
    else if (bypass) {
        javax.xml.ws.Dispatch dispatch = sampleAccountsPortProxyid._getDescriptor().getDispatch();

        if (request.getParameter("__use_soapaction__") != null)
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.useSoapAction(dispatch, request.getParameter("__soapaction__"));
        else
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.clearSoapAction(dispatch);

        if (!async)
            sourceOut = (javax.xml.transform.Source) dispatch.invoke(sourceIn);
        else {
            // async code omitted
            break;
        }
    } else {
        String accountNumber_4id=  request.getParameter("accountNumber21");
        long accountNumber_4idTemp  = Long.parseLong(accountNumber_4id);

        String amount_6id=  request.getParameter("amount23");
        String amount23null = request.getParameter("amount23null");
        java.lang.Double amount_6idTemp;
        if (amount23null != null)
            amount_6idTemp = null;
        else {
         amount_6idTemp  = java.lang.Double.valueOf(amount_6id);
        }
        javax.xml.ws.Holder amount_6idTemp_h = new javax.xml.ws.Holder();
        amount_6idTemp_h.value = amount_6idTemp;

        if (!async) {
        try {
            sampleAccountsPortProxyid.withdraw(accountNumber_4idTemp,amount_6idTemp_h);
            amount23mtemp = (java.lang.Double) amount_6idTemp_h.value;
            }catch(com.example.services.banking.InsufficientFunds InsufficientFunds25){
            com.example.schemas.banking.InsufficientFunds InsufficientFunds26 = InsufficientFunds25.getFaultInfo();
                %>
<TABLE CLASS="tableform">
<TR>
<TD COLSPAN="2" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">com.example.services.banking.InsufficientFunds:</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="1" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">errorMessage:</TD>
<TD>
<%
if(InsufficientFunds26 != null){
java.lang.String typeerrorMessage27 = InsufficientFunds26.getErrorMessage();
if(typeerrorMessage27 != null){
        String tempResulterrorMessage27 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeerrorMessage27));
        %>
        <%= tempResulterrorMessage27 %>
        <%
}
else{
        %>
        <%= typeerrorMessage27%>
        <%
}
}
%>
</TD>
<TR>
<TD CLASS="spacercol">&nbsp;</TD>
<TD COLSPAN="1" VALIGN="TOP" ALIGN="LEFT" CLASS="headingcol">errorCode:</TD>
<TD>
<%
if(InsufficientFunds26 != null){
int typeerrorCode29 = InsufficientFunds26.getErrorCode();
        String tempResulterrorCode29 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeerrorCode29));
        %>
        <%= tempResulterrorCode29 %>
        <%
}
%>
</TD>
</TABLE>
                <%
break;
            }catch(Exception exc){
                %>
                Exception: <%= exc %>
                Message: <%= exc.getMessage() %>
                <%
break;
            }
        }
        else {
            // async code omitted
            break;
        }
    }
if (sourceOut != null) {
%>
    <TEXTAREA ROWS="8" COLs="45"><%= org.eclipse.jst.ws.util.JspUtils.markup(com.ibm.ccl.ws.jaxws.gstc.util.SourceUtils.transform(sourceOut)) %></TEXTAREA>
<%
}
else {
if (amount23mtemp == null) {
%>
    null
<%
} else {
        String tempResultamount23 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(amount23mtemp));
        %>
        <%= tempResultamount23 %>
        <%
}
%>
<HR/><BR/>
<%
}
break;
case 31:
    gotMethod = true;
    java.lang.Double balance36mtemp = null;
    java.lang.String owner38mtemp = null;
    if (methodKey != null) {
        javax.xml.ws.Response resp = com.ibm.ccl.ws.jaxws.gstc.util.AsyncUtils.getResponse(session, methodKey);
        if (resp != null) {
            isDone = resp.isDone();
            if (!isDone)
                break;
            if (resp.get() != null) {
                Object o = resp.get();
                if (o instanceof javax.xml.transform.Source)
                    sourceOut = (javax.xml.transform.Source) o;
            }
        }
    }
    else if (bypass) {
        javax.xml.ws.Dispatch dispatch = sampleAccountsPortProxyid._getDescriptor().getDispatch();

        if (request.getParameter("__use_soapaction__") != null)
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.useSoapAction(dispatch, request.getParameter("__soapaction__"));
        else
            com.ibm.ccl.ws.jaxws.gstc.util.DispatchUtils.clearSoapAction(dispatch);

        if (!async)
            sourceOut = (javax.xml.transform.Source) dispatch.invoke(sourceIn);
        else {
            // async code omitted
            break;
        }
    } else {
        String accountNumber_8id=  request.getParameter("accountNumber34");
        long accountNumber_8idTemp  = Long.parseLong(accountNumber_8id);

        javax.xml.ws.Holder balance36_h = new javax.xml.ws.Holder();

        javax.xml.ws.Holder owner38_h = new javax.xml.ws.Holder();

        if (!async) {
        try {
            sampleAccountsPortProxyid.getAccountInfo(accountNumber_8idTemp,balance36_h,owner38_h);
            balance36mtemp = (java.lang.Double) balance36_h.value;
            owner38mtemp = (java.lang.String) owner38_h.value;
            }catch(Exception exc){
                %>
                Exception: <%= exc %>
                Message: <%= exc.getMessage() %>
                <%
break;
            }
        }
        else {
            // async code omitted
            break;
        }
    }
if (sourceOut != null) {
%>
    <TEXTAREA ROWS="8" COLs="45"><%= org.eclipse.jst.ws.util.JspUtils.markup(com.ibm.ccl.ws.jaxws.gstc.util.SourceUtils.transform(sourceOut)) %></TEXTAREA>
<%
}
else {
if (balance36mtemp == null) {
%>
    null
<%
} else {
        String tempResultbalance36 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(balance36mtemp));
        %>
        <%= tempResultbalance36 %>
        <%
}
%>
<HR/><BR/>
<%
if (owner38mtemp == null) {
%>
    null
<%
} else {
        String tempResultowner38 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(owner38mtemp));
        %>
        <%= tempResultowner38 %>
        <%
}
%>
<HR/><BR/>
<%
}
break;
}
} catch (Exception e) { 
%>
exception: <%= e %>
<%
return;
}
if(!gotMethod){
%>
Result: N/A
<%
} else if (!isDone) {
%>
No results available yet.
<%
} else if (async && methodKey == null) {
%>
The service has been invoked.
<script language="JavaScript">reloadMethods();</script>
<%
}
%>
</BODY>
</HTML>
/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.8
 * Generated at: 2017-02-08 07:05:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class createPayment_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/home/khaled/workspacePaymentNew/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web-war/WEB-INF/lib/standard-1.1.2.jar!/META-INF/c.tld", Long.valueOf(1098703890000L));
    _jspx_dependants.put("/WEB-INF/lib/standard-1.1.2.jar", Long.valueOf(1481186175000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<form class=\"well form-horizontal\" action=\"./submitpaymentrequest\"\n");
      out.write("\tmethod=\"post\" enctype=\"application/x-www-form-urlencoded\">\n");
      out.write("\t<fieldset>\n");
      out.write("\t\t<!-- Form Name -->\n");
      out.write("\t\t<legend>Create Payment Request</legend>\n");
      out.write("\t\t<!-- Select Basic -->\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"col-md-4 control-label\" for=\"orderingAccountIBAN\">\n");
      out.write("\t\t\t\tUser Accounts </label>\n");
      out.write("\t\t\t<div class=\"col-md-4 selectContainer\">\n");
      out.write("\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t<span class=\"input-group-addon\"> <i\n");
      out.write("\t\t\t\t\t\tclass=\"glyphicon glyphicon-list\"></i>\n");
      out.write("\t\t\t\t\t</span> <select class=\"form-control selectpicker\" id=\"orderingAccountIban\"\n");
      out.write("\t\t\t\t\t\tname=\"orderingAccountIban\">\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- Text input-->\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"col-md-4 control-label\" for=\"beneficiaryAccountIBAN\">beneficiary\n");
      out.write("\t\t\t\tAccount </label>\n");
      out.write("\t\t\t<div class=\"col-md-4 inputGroupContainer\">\n");
      out.write("\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t<span class=\"input-group-addon\"> <i\n");
      out.write("\t\t\t\t\t\tclass=\"glyphicon glyphicon-user\"></i>\n");
      out.write("\t\t\t\t\t</span> <input class=\"form-control\" type=\"text\"\n");
      out.write("\t\t\t\t\t\tplaceholder=\"Beneficiary Account Iban\" value=\"\"\n");
      out.write("\t\t\t\t\t\tid=\"beneficiaryAccountIban\" name=\"beneficiaryAccountIban\" required>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t<!-- Text input-->\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"col-md-4 control-label\" for=\"beneficiaryName\">beneficiary\n");
      out.write("\t\t\t\tName</label>\n");
      out.write("\t\t\t<div class=\"col-md-4 inputGroupContainer\">\n");
      out.write("\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t<span class=\"input-group-addon\"> <i\n");
      out.write("\t\t\t\t\t\tclass=\"glyphicon glyphicon-envelope\"></i>\n");
      out.write("\t\t\t\t\t</span> <input placeholder=\"Beneficiary Name\" class=\"form-control\"\n");
      out.write("\t\t\t\t\t\ttype=\"text\" id=\"beneficiaryName\" name=\"beneficiaryName\" value=\"\"\n");
      out.write("\t\t\t\t\t\trequired>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- Text input-->\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"col-md-4 control-label\" for=\"paymentAmount\">\n");
      out.write("\t\t\t\tPayment Amount </label>\n");
      out.write("\t\t\t<div class=\"col-md-4 inputGroupContainer\">\n");
      out.write("\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t<span class=\"input-group-addon\"> <i\n");
      out.write("\t\t\t\t\t\tclass=\"glyphicon glyphicon-envelope\"></i>\n");
      out.write("\t\t\t\t\t</span> <input placeholder=\"Payment Amount\" class=\"form-control\"\n");
      out.write("\t\t\t\t\t\ttype=\"text\" id=\"paymentAmount\" name=\"paymentAmount\" value=\"\"\n");
      out.write("\t\t\t\t\t\trequired>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- Select Basic -->\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"col-md-4 control-label\" for=\"checkLang\"> Check\n");
      out.write("\t\t\t\tWriting Language </label>\n");
      out.write("\t\t\t<div class=\"col-md-4 selectContainer\">\n");
      out.write("\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t<span class=\"input-group-addon\"> <i\n");
      out.write("\t\t\t\t\t\tclass=\"glyphicon glyphicon-list\"></i>\n");
      out.write("\t\t\t\t\t</span> <select class=\"form-control selectpicker\" id=\"checkLang\"\n");
      out.write("\t\t\t\t\t\tname=\"checkLang\">\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- Select Basic -->\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"col-md-4 control-label\" for=\"currencyCode\">\n");
      out.write("\t\t\t\tCurrency Code </label>\n");
      out.write("\t\t\t<div class=\"col-md-4 selectContainer\">\n");
      out.write("\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t<span class=\"input-group-addon\"> <i\n");
      out.write("\t\t\t\t\t\tclass=\"glyphicon glyphicon-list\"></i>\n");
      out.write("\t\t\t\t\t</span> <select class=\"form-control selectpicker\" id=\"currencyCode\"\n");
      out.write("\t\t\t\t\t\tname=\"currencyCode\">\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f2(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"col-md-4 control-label\" for=\"purposeCode\">\n");
      out.write("\t\t\t\tPurpose Code </label>\n");
      out.write("\t\t\t<div class=\"col-md-4 selectContainer\">\n");
      out.write("\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t<span class=\"input-group-addon\"> <i\n");
      out.write("\t\t\t\t\t\tclass=\"glyphicon glyphicon-list\"></i>\n");
      out.write("\t\t\t\t\t</span> <select class=\"form-control selectpicker\" id=\"purposeCode\"\n");
      out.write("\t\t\t\t\t\tname=\"purposeCode\">\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f3(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t</select>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- Select Basic -->\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"col-md-4 control-label\" for=\"paymentDate\">\n");
      out.write("\t\t\t\tPayment Date </label>\n");
      out.write("\t\t\t<div class=\"col-md-4 selectContainer\">\n");
      out.write("\t\t\t\t<div class=\"input-group\">\n");
      out.write("\t\t\t\t\t<span class=\"input-group-addon\"> <i\n");
      out.write("\t\t\t\t\t\tclass=\"glyphicon glyphicon-list\"></i>\n");
      out.write("\t\t\t\t\t</span> <input class=\"form-control\" type=\"date\" id=\"paymentDate\"\n");
      out.write("\t\t\t\t\t\tname=\"paymentDate\" value=\"\" required>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- Button -->\n");
      out.write("\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t<label class=\"col-md-4 control-label\"></label>\n");
      out.write("\t\t\t<div class=\"col-md-4\">\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"go_back_and_edit\" id=\"go_back_and_edit\"\n");
      out.write("\t\t\t\t\tvalue=\"0\" />\n");
      out.write("\t\t\t\t<button type=\"submit\" class=\"btn btn-warning\">\n");
      out.write("\t\t\t\t\tSubmit <span class=\"glyphicon glyphicon-send\"></span>\n");
      out.write("\t\t\t\t</button>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\n");
      out.write("\t\t<!-- Success message -->\n");
      out.write("\t\t<div class=\"alert alert-success\" role=\"alert\" id=\"success_message\">\n");
      out.write("\t\t\tSuccess <i class=\"glyphicon glyphicon-thumbs-up\"></i> Thanks for\n");
      out.write("\t\t\tcontacting us, we will get back to you shortly.\n");
      out.write("\t\t</div>\n");
      out.write("\t</fieldset>\n");
      out.write("</form>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    try {
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /WEB-INF/views/createPayment.jsp(21,6) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${accounts}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      // /WEB-INF/views/createPayment.jsp(21,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("account");
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n");
            out.write("\t\t\t\t\t\t\t<option ");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${account.iban eq orderingAccountIban ?'selected' :'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("\n");
            out.write("\t\t\t\t\t\t\t\tvalue=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${account.iban}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${account.iban}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</option>\n");
            out.write("\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
      }
    } finally {
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    try {
      _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f1.setParent(null);
      // /WEB-INF/views/createPayment.jsp(83,6) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${checkLang}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      // /WEB-INF/views/createPayment.jsp(83,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setVar("lang");
      int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
        if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n");
            out.write("\t\t\t\t\t\t\t<option value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lang}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lang}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</option>\n");
            out.write("\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f1.doFinally();
      }
    } finally {
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    try {
      _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f2.setParent(null);
      // /WEB-INF/views/createPayment.jsp(100,6) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f2.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currencies}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      // /WEB-INF/views/createPayment.jsp(100,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f2.setVar("currency");
      int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
        if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n");
            out.write("\t\t\t\t\t\t\t<option value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currency.code}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currency.code}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</option>\n");
            out.write("\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f2.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f2[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f2.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f2.doFinally();
      }
    } finally {
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f2);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f3 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    try {
      _jspx_th_c_005fforEach_005f3.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f3.setParent(null);
      // /WEB-INF/views/createPayment.jsp(117,6) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f3.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${paymentPurposes}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      // /WEB-INF/views/createPayment.jsp(117,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f3.setVar("purpose");
      int[] _jspx_push_body_count_c_005fforEach_005f3 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f3 = _jspx_th_c_005fforEach_005f3.doStartTag();
        if (_jspx_eval_c_005fforEach_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\n");
            out.write("\t\t\t\t\t\t\t<option value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${purpose.code}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${purpose.code}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
            out.write("</option>\n");
            out.write("\t\t\t\t\t\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f3.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f3[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f3.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f3.doFinally();
      }
    } finally {
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f3);
    }
    return false;
  }
}

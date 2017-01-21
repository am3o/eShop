<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html >
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title><s:text name="startpage.head"/></title>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap/css/custom.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="http://www.iwi.hs-karlsruhe.de">Informatik</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="<s:url action="listAllProducts" />">Alle Produkte</a>
                    <s:if test="#session.webshop_user.role=='Admin'">   <!-- if admin -->
                <li><a href="./InitCategorySiteAction.action?pageToGoTo=p"><s:text
                        name="product.add"/></a></li>
                <li><a href="./InitCategorySiteAction.action?pageToGoTo=c"><s:text
                        name="categories.edit"/></a></li>
                </s:if>
            </ul>
            <div>
                <nav class="nav navbar-nav navbar-right">
                    <li><a href="<s:url action = "LogoutAction"/>">Logout</a></li>
                </nav>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <s:text name="login.status"/> ${webshop_user.name} ${webshop_user.lastname}
    </div>
    <div class="row">
        <div class="col-xs-8">
            <h3>
                <s:text name="search.title"/>
            </h3>
            <s:form action="SearchAction" theme="simple">
                <div class="form-group">
                    <label>Suchtext-DETAILS:</label>
                    <s:textfield name="searchValue" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label>Preis min.:</label>
                    <s:textfield name="searchMinPrice" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <label>Preis max.:</label>
                    <s:textfield name="searchMaxPrice" cssClass="form-control"/>
                </div>
                <div>
                    <s:submit method="execute" key="search.submit" cssClass="btn btn-success"/>
                </div>
            </s:form>
        </div>
    </div>

    <div class="row">
        <h3>
            <s:text name="products.title"/>
        </h3>
    </div>


    <div id="startpage_products">

        <table class="table table-striped">

            <tr class="header">
                <td><s:text name="product.nr"/></td>
                <td><s:text name="product.category"/></td>
                <td><s:text name="product.name"/></td>
                <td><s:text name="product.details"/></td>
                <td><s:text name="product.price"/></td>
                <td></td>
            </tr>
            <s:iterator value="products" status="rowstatus">
                <tr>
                    <td class="odd"><s:property value="#rowstatus.index+1"/></td>
                    <td class="odd"><s:property value="categoryName" default="undefined"/></td>
                    <td class="odd"><s:property value="name"/></td>
                    <td class="odd"><s:property value="details"/></td>
                    <td class="odd"><s:property value="price"/></td>
                    <td class="odd"><a
                            href="./ProductDetailsAction.action?id=<s:property value='id'/>"><span
                            class="glyphicon glyphicon-option-vertical"
                            aria-hidden="true"></span></a>
                    </td>
                    <s:if test="#session.webshop_user.role=='Admin'">
                        <td class="odd"><a
                                href="./DeleteProductAction.action?id=<s:property value='id'/>"><span
                                class="glyphicon glyphicon-remove"
                                aria-hidden="true"></span></a>
                        </td>
                    </s:if>
                </tr>
            </s:iterator>
        </table>
    </div>
</div>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="top_admin.jsp"/>

        <section id="content" class="table-layout animated fadeIn">
            <div class="tray tray-center">
                <div class="content-header">
                    <h2> 修改密码 </h2>
                    <p class="lead"></p>
                </div>
                <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
                    <div class="panel heading-border">
                        <form method="post" action="users_change_password" id="admin-form">
                            <div class="panel-body bg-light">
                                <div class="section-divider mt20 mb40">
                                    <span> 基本信息 </span>
                                </div>
                                <div class="section row">
                                    <div class="col-md-6">
                                        <label for="admin_password" class="field prepend-icon">
                                            <input type="password" name="admin_password" id="admin_password" class="gui-input" placeholder="原始密码">
                                            <label for="admin_password" class="field-icon">
                                                <i class="fa fa-lock"></i>
                                            </label>
                                        </label>
                                    </div>
                                </div>
                                <div class="section row">
                                    <div class="col-md-6">
                                        <label for="newPassword3" class="field prepend-icon">
                                            <input type="password" name="newPassword3" id="newPassword3" class="gui-input" placeholder="新密码">
                                            <label for="newPassword3" class="field-icon">
                                                <i class="fa fa-lock"></i>
                                            </label>
                                        </label>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="newPassword4" class="field prepend-icon">
                                            <input type="password" name="newPassword4" id="newPassword4" class="gui-input" placeholder="确认密码">
                                            <label for="newPassword4" class="field-icon">
                                                <i class="fa fa-lock"></i>
                                            </label>
                                        </label>
                                    </div>
                                </div>
                                <div class="panel-footer text-right">
                                    <button type="submit" class="button"> 修改 </button>
                                    <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回 </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

<jsp:include page="bottom_admin.jsp"/>
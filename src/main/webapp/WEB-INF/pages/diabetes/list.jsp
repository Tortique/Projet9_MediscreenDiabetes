<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Abernathy Clinic</title>
</head>

<body>
<div class="container">
    <div class="row" style="margin-left: 40%">
        <div
                class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
            <h1>Abernathy Clinic - Mediscreen</h1>
            <h2>Patient's Diabetes Report</h2>
        </div>
    </div>
    <div class="row" style="margin-left: 20%">
        <div class="form-group">
            <div>
                <a href="/home">Home</a>
            </div>
        </div>
        <div>
            <table class="table table-striped table-bordered" style="width: 100%" id="patientTable">
                <thead>
                <tr>
                    <th>LastName</th>
                    <th>FirstName</th>
                    <th>Age</th>
                    <th>Diabetes Assessment Result</th>
                </tr>
                </thead>
                <tbody style="width: 100%">
                    <tr>
                        <td><c:out value="${diabetes.lastName}"/></td>
                        <td><c:out value="${diabetes.firstName}"/></td>
                        <td><c:out value="${diabetes.age}"/></td>
                        <td><c:out value="${diabetes.diabetesAssessmentResult}"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
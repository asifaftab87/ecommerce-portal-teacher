<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            body {
                font-family: Arial, Helvetica, sans-serif;
            }
            
            form {
                border: 3px solid #f1f1f1;
            }
            
            input[type=text],
            input[type=password] {
                width: 40%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            
            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 40%;
            }
            
            button:hover {
                opacity: 0.8;
            }
            
            .signup {
                width: auto;
                padding: 10px 18px;
                background-color: #4CAF50;
                color: white;
                border: none;
            }
            
            input[type=submit]:hover {
                opacity: 0.8;
            }
            
            .imgcontainer {
                text-align: center;
                margin: 20px 0 1px 0;
            }
            
            img.pic {
                width: 40%;
                border-radius: 50%;
            }
            
            .container {
                padding: 16px;
            }
            
            span.psw {
                float: right;
                padding-top: 16px;
            }
            /* Change styles for span and signup button on extra small screens */
            
            @media screen and (max-width: 300px) {
                span.psw {
                    display: block;
                    float: none;
                }
                .signup {
                    width: 40%;
                }
            }
            
            .div2 {
                text-align: center;
            }
        </style>
    </head>

    <body>
        <h2 style="text-align:center">Log-in</h2>
        <form action="/authenticate" method="post" modelAttribute="req">
            <div class="div2">
                <input type="text" placeholder="Enter username" name="username" required><br>
                <input type="password" placeholder="Enter Password" name="password" required><br>
                <button type="submit">Login</button><br>
            </div>
        </form>
        <div class="container" style="background-color:#f1f1f1">
            <form action="/singup" method="get">
                <label>Don't have an account?</label><br><br>
                <input type="submit" class="signup" value="Signup" />
            </form>
        </div>
    </body>

    </html>
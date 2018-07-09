<div class="row">
    <div class="col-4">LOGO (Link to home)</div>
    <div class="col-8">Pub (Changer a chaque page en mode random + link)</div>
</div>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(TODO )</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/presentation">Presentation</a>
            </li>
        </ul>
        <ul class="navbar-nav navbar-right">
            <c:if test="${ empty user }">
                <li class="nav-item">
                    <a class="nav-link" href="/create-user">Inscription</a>
                </li>
                <form class="form-inline my-2 my-lg-0" action="/connection" method="post">
                    <input class="form-control mr-sm-2" type="mail" placeholder="Mail" aria-label="Mail" name="email">
                    <input class="form-control mr-sm-2" type="password" placeholder="Password" aria-label="Password" name="password">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Connection</button>
                </form>
            </c:if>
            <c:if test="${ !empty user }">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${ user.login }
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/my-account">Mon Compte</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/logout">Logout</a>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
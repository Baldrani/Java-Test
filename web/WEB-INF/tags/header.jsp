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
            <!--  TODO CHANGER le sr-only en fonction de la page + link active -->
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(TODO )</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/presentation">Presentation</a>
            </li>
        </ul>
        <ul class="navbar-nav navbar-right">
            <li class="nav-item">
                <a class="nav-link" href="/create-user">Inscription</a>
            </li>
            <form class="form-inline my-2 my-lg-0" action="/connection">
                <input class="form-control mr-sm-2" type="mail" placeholder="Mail" aria-label="Mail">
                <input class="form-control mr-sm-2" type="password" placeholder="Password" aria-label="Password">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Connection</button>
            </form>
        </ul>

        <!-- TODO IF LOGED IN LOGOUT BUTTON -->
    </div>
</nav>
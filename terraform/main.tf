resource "heroku_app" "default" {
  name = "vermietet-challenge-coding"
  region = "us"
}

resource "heroku_addon" "database" {
  app  = "${heroku_app.default.name}"
  plan = "heroku-postgresql:hobby-dev"
}
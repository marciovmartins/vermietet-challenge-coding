provider "heroku" {
  email = "${var.email}"
  api_key = "${var.heroku_api_key}"
}

resource "heroku_app" "default" {
  name = "vermietet-challenge-coding"
  region = "us"
  buildpacks = [
    "heroku/java"
  ]
  config_vars = {
    ENVIRONMENT = "heroku"
  }
}

resource "heroku_addon" "database" {
  app = "${heroku_app.default.name}"
  plan = "heroku-postgresql:hobby-dev"
}
package controllers

import models.{Movie, MovieRepository}
import play.api.mvc._
import javax.inject._
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class MovieController @Inject()(cc: ControllerComponents, movieRepository: MovieRepository) extends AbstractController(cc) {

  implicit val serializador = Json.format[Movie]

  def getMovies = Action.async{
    movieRepository
      .getAll
      .map(movies => {
        val j = Json.obj(
          "data" -> movies,
          "message" -> "Movies listed"
        )
        Ok(j)
      })
  }

  def getMovie(id: String) = Action.async {
    movieRepository
      .getAll
      .map(movie => {
        val j = Json.obj(
          "data" -> movie,
          "message" -> "Movies listed"
        )
        Ok(j)
      })
  }
  def createMovie = ???
  def updateMovie = ???
  def deleteMovie = ???
}

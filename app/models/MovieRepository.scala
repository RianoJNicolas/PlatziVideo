package models

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc._
import slick.jdbc.SQLiteProfile.api._
import slick.lifted.TableQuery

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class MovieRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)
                               (implicit ec: ExecutionContext)
  extends AbstractController(cc)
    with HasDatabaseConfigProvider[JdbcProfile] {

  private lazy val movieQuery = TableQuery[MovieTable]

  def dbInit: Future[Unit] = {
    // Definición de la sentencia SQL de creación del schema
    val createSchema = movieQuery.schema.createIfNotExists
    // db.run Ejecuta una sentencia SQL, devolviendo un Future
    db.run(createSchema)
  }

  def getAll = {
    val q = movieQuery.sortBy(_.id)
    db.run(q.result)
  }
  def getOne(id: String) = {
    val q = movieQuery.filter(_.id === id )
    db.run(q.result.headOption)
  }
  def create = ???
  def update = ???
  def delete = ???
}

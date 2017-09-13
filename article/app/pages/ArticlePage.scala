package pages

import html.{HtmlPage, Styles}
import model.{ApplicationContext, PressedPage}
import play.api.mvc.RequestHeader
import play.twirl.api.Html
import views.html.fragments._
import views.html.fragments.page._
import views.html.fragments.page.head._
import views.html.fragments.page.body._
import views.html.fragments.page.head.stylesheets._

object ArticlePage extends HtmlPage[PressedPage] {

  def allStyles(implicit applicationContext: ApplicationContext) = new Styles {
    override def criticalCss: Html = criticalStyles(Some("facia"))
    override def linkCss: Html = stylesheetLink("stylesheets/facia.css")
    override def oldIECriticalCss: Html = stylesheetLink("stylesheets/old-ie.head.facia.css")
    override def oldIELinkCss: Html = stylesheetLink("stylesheets/old-ie.content.css")
    override def IE9LinkCss: Html = stylesheetLink("stylesheets/ie9.head.facia.css")
    override def IE9CriticalCss: Html = stylesheetLink("stylesheets/ie9.content.css")
  }

  def html(implicit page: PressedPage, request: RequestHeader, applicationContext: ApplicationContext): Html = {
    htmlTag(
      headTag(
        titleTag(),
        metaData(),
        // article meta
        styles(allStyles), //TODO: remove duplication?
        fixIEReferenceErrors(),
        inlineJSBlocking()
      ),
      bodyTag(classes = defaultBodyClasses)(
        message(),
        skipToMainContent(),
        pageSkin(),
        survey(),
        headerHtml(),
        breakingNewsDiv(),
        cleanFrontBody(),
        footer(),
        inlineJSNonBlocking(),
        analytics.base()
      ),
      devTakeShot()
    )
  }

}

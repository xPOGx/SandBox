package idp.cs.pattern

fun main() {
    val sys = SystemNotifier()
    sys.notifyService()
    val tel = TelegramNotifier()
    tel.notifyService()
    val slack = SlackNotifier(tel)
    slack.notifyService()
}

interface Notifier {
    fun notifyService()
}

open class SystemNotifier : Notifier {
    override fun notifyService() {
        println("System notification\n")
    }
}

class TelegramNotifier : SystemNotifier() {
    override fun notifyService() {
        println("Telegram Notification")
        super.notifyService()
    }
}

class SlackNotifier(private val notifier: Notifier) : Notifier {
    override fun notifyService() {
        println("Slack notification")
        notifier.notifyService()
    }
}

package engineer.easy.dev.ui

import engineer.easy.dev.util.DuCmd
import engineer.easy.internal.Page
import engineer.easy.internal.interfaces.CmdInterface
import engineer.easy.internal.util.Printer
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.*
import java.util.Timer
import javax.swing.*

class DuPage : Page(), ActionListener {

    private var openPackage = JButton("Open")
    private var openDirect = JButton("Cmd-Open")
    private var clearBtn = JButton("清屏")
    private val packageInput = JTextField("")
    private val openDirectInput = JTextField("MainActivity")
    private val screenRecord = JButton("录屏")
    private val stopRecord = JButton(STOP)
    private val pullFile = JButton("Pull File")
    private val debugUIBtn = JButton(DEBUG_UI_SHOW)

    private val console = JTextArea(18, 54)

    private var realCmd: CmdInterface = DuCmd()

    override fun open() {
        initUI()

        Printer.init(console)
        isVisible = true

        realCmd.init()
    }

    private fun initUI() {
        this.title = "NoCmd"
        setSize(625, 480)

        isResizable = false
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE

        val flowLayout = FlowLayout(FlowLayout.LEFT)
        layout = flowLayout

        val packageName = JLabel("open-command：")
        packageName.preferredSize = Dimension(LABEL_WIDTH, HEIGHT)
        add(packageName)
        packageInput.preferredSize = Dimension(INPUT_WIDTH, HEIGHT)
        add(packageInput)
        openPackage.addActionListener(this)
        add(openPackage)
        clearBtn.addActionListener(this)
        add(clearBtn)

        this.addBlank(40, HEIGHT)
        val opeDirect = JLabel("cmd open：")
        opeDirect.preferredSize = Dimension(LABEL_WIDTH, HEIGHT)
        add(opeDirect)
        openDirectInput.preferredSize = Dimension(INPUT_WIDTH, HEIGHT)
        add(openDirectInput)
        openDirect.addActionListener(this)
        add(openDirect)
        this.addBlank(50, HEIGHT)

        screenRecord.addActionListener(this)
        add(screenRecord)
        stopRecord.addActionListener(this)
        add(stopRecord)
        pullFile.addActionListener(this)
        add(pullFile)
        debugUIBtn.addActionListener(this)
        add(debugUIBtn)


        /**
         * console
         */
        addBlank(20, HEIGHT)
        console.isEditable = false
        val js = JScrollPane(console)
        // 分别设置水平和垂直滚动条出现方式
        // 分别设置水平和垂直滚动条出现方式
        js.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        js.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        js.isWheelScrollingEnabled = true
//        js.autoscrolls = true
//        js.verticalScrollBar.addAdjustmentListener {
//            it.adjustable.value = it.adjustable.maximum
//        }
        this.add(js)


    }

    private fun addBlank(width: Int, height: Int) {
        val blank = JLabel()
        blank.preferredSize = Dimension(width, height)
        add(blank)
    }

    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            openPackage -> {
                val packageName = packageInput.text
                if (packageName.isNullOrBlank()) {

                } else {
                    execute(packageName)
                }
            }
            clearBtn -> {
                Printer.clear()
            }
            openDirect -> {
                val arg = openDirectInput.text
                if (arg.isNullOrBlank()) {

                } else {
                    realCmd.open(arg)
                }
            }
            screenRecord -> {
                val text = screenRecord.text
                var arg = ""
                if (text.equals(SCREEN)) {
                    arg = "adb shell screenrecord --size 1280x720 /sdcard/demo.mp4"
//                    realCmd.runAs(arg)
                    screenRecord.text = STOP
                    task = MyTask(arg)
                    timer.schedule(task, 0)
                } else {
                    screenRecord.text = SCREEN
//                    task?.cancel()
//                    timer.cancel()
                }
            }
            stopRecord -> {
                realCmd.runAs("taskkill")
            }
            pullFile -> {
                val arg = "adb pull /sdcard/demo.mp4 ."
                Thread {
                    realCmd.runAs(arg)
                }.start()
            }
            debugUIBtn -> {
                val text = debugUIBtn.text
                var arg = ""
                if (text == DEBUG_UI_SHOW) {
                    arg = "$ADB_PROP_LAYOUT true"
                    debugUIBtn.text = DEBUG_UI_HIDE
                } else {
                    arg = "$ADB_PROP_LAYOUT false"
                    debugUIBtn.text = DEBUG_UI_SHOW
                }
                execute(arg)
            }
        }
    }

    private fun execute(arg: String) {
        realCmd.executeCmd(arg)
    }

    private var task: MyTask? = null
    private val timer = Timer()

    private inner class MyTask(val arg: String) : TimerTask() {


        override fun run() {
            realCmd.runAs(arg)
        }
    }

    companion object {
        val HEIGHT = 30
        val LABEL_WIDTH = 110
        val INPUT_WIDTH = 280
    }
}
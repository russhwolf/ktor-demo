import UIKit
import app

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        SampleKt.webcall()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    @IBOutlet weak var label: UILabel!
}

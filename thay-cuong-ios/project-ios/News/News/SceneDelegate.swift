//
//  SceneDelegate.swift
//  News
//
//  Created by CNTT on 06/04/21.
//

import UIKit //la 1 module chua class, struct, enum dung de tao UI

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?
    //
    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        // Use this method to optionally configure and attach the UIWindow `window` to the provided UIWindowScene `scene`.
        // If using a storyboard, the `window` property will automatically be initialized and attached to the scene.
        // This delegate does not imply the connecting scene or session are new (see `application:configurationForConnectingSceneSession` instead).
        guard let _ = (scene as? UIWindowScene) else { return }
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.window = self.window
    }
}


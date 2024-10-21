# CLIframe

CLIframe is a small framework that aids in the creation of simple cli programs. The main features are drawing frames of a given size, horizontal divisors, text, auto centered text and clearing the frame.

⚠ **CLIframe requires ansi codes to be enabled to run properly**
- Windows:
```
reg add HKEY_CURRENT_USER\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000001 /f >NUL
```

⚠ **CLIframe requires a monospaced font**

## Using CLIframe

To use CLIframe you only need the Frame.java file, you can drop it into your project and make calls to the public functions. Two example programs can be found in the App.java and Demo.java files, you could also read the functions in Frame.java to get a better understating. JavaDoc will be added in the future.
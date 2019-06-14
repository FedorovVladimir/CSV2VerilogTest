package tdhd.project;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

class GitVersionControlSystem {
    boolean gitClone(String url, String absoluteFolderPath) {
        try {
            Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(new File(absoluteFolderPath))
                    .call();
            writeLog("git clone " + url);
            return true;
        } catch (GitAPIException e) {
            writeLog("git clone " + url + " fail");
            return false;
        }
    }

    void gitCommit(String message, String absoluteFolderPath) {
        File gitFile = new File(absoluteFolderPath + "\\.git");
        try {
            Git.open(gitFile)
                    .add().addFilepattern(".").call();
            Git.open(gitFile)
                    .commit().setMessage(message).call();
            writeLog("git commit '" + message + "'");
        } catch (GitAPIException | IOException e) {
            writeLog("git commit '" + message + "' fail");
        }
    }

    boolean gitPush(String login, String password, String absoluteFolderPath) {
        File gitFile = new File(absoluteFolderPath + "\\.git");
        try {
            Git.open(gitFile)
                    .push()
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(login, password))
                    .call();
            writeLog("git push");
            return true;
        } catch (GitAPIException | IOException e) {
            writeLog("git push fail");
            return false;
        }
    }

    private void writeLog(String text) {
        System.out.println("Git massege: " + text);
    }
}

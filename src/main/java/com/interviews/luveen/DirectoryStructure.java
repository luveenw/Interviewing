package com.interviews.luveen;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @interview {Su} Google BSI/SWE On-site 2012
 *
 * <ol>
 *     <li>Given a List of Strings with absolute file paths, construct a directory tree structure.</li>
 *     <li>Given a directory structure, print out the paths for all files encountered.</li>
 * </ol>
 */
public class DirectoryStructure {
    private static final String DIR_SEPARATOR = "/";

    public static void main(String[] args) {
        /*DirNode root = new DirNode(DIR_SEPARATOR);
        DirNode a = new DirNode("a");
        DirNode c = new DirNode("c");

        a.addFile("b.txt");
        c.addFile("2.png");
        a.addDir(c);
        root.addDir(a);

        System.out.println("File paths for root:");
        getFilePathsFor1(root).forEach(System.out::println);

        Arrays.stream("/a/b/1.txt".split("/")).forEach(part -> System.out.println(String.format("[%s]", part)));*/

        List<String> paths = Lists.newArrayList(
                "/a/b/c.txt",
                "/a/1.png",
                "/.bashrc",
                "/"
        );

        DirNode root1 = createDirStructureFor(paths);

        System.out.println("File paths for root1:");
        getFilePathsFor(root1, "").forEach(System.out::println);
    }

    private static List<String> getFilePathsFor(DirNode root, String prefix) {
        List<String> result = new ArrayList<>();

        if (root.hasFiles()) {
            root.fileNames
                    .forEach(fName -> result.add(prefix + DIR_SEPARATOR + fName));
        }

        if (root.hasDirs()) {
            root.dirs.values()
                    .forEach(dirNode -> result.addAll(
                            getFilePathsFor(dirNode, prefix + DIR_SEPARATOR + dirNode.dirName)));
        }

        return result;
    }

    private static List<String> getFilePathsFor1(DirNode root) {
        ArrayDeque<DirAndPrefix> que = new ArrayDeque<>();
        List<String> result = new ArrayList<>();

        que.add(new DirAndPrefix(root, ""));

        while (!que.isEmpty()) {
            DirAndPrefix dp = que.remove();
            DirNode dir = dp.dir;
            String prefix = dp.prefix;

            if (dir.hasFiles()) {
                dir.fileNames
                        .forEach(fName -> result.add(prefix + DIR_SEPARATOR + fName));
            }

            if (root.hasDirs()) {
                dir.dirs.values()
                        .forEach(dirNode -> que.add(new DirAndPrefix(dirNode, prefix + DIR_SEPARATOR + dirNode.dirName)));
            }
        }

        return result;
    }

    private static DirNode createDirStructureFor(List<String> paths) {
        DirNode root = new DirNode("");

        for (String path : paths) {
            StringTokenizer st = new StringTokenizer(path, DIR_SEPARATOR);

            DirNode node = root;
//            boolean thereShouldBeNoMore = false;

            while(st.hasMoreTokens()) {
/*
                if (thereShouldBeNoMore) {
                    break;
                }
*/

                String token = st.nextToken();

                if (isFileName(token)) {
                    node.addFile(token);
//                    thereShouldBeNoMore = true;
                } else if (isDirName(token)) {
                    if (node.hasDir(token)) {
                        node = node.dirs.get(token);
                    } else {
                        DirNode temp = new DirNode(token);

                        node.addDir(temp);
                        node = temp;
                    }
                }
            }
        }

        return root;
    }

    private static boolean isDirName(String token) {
        return !token.isEmpty() && token.indexOf('.') < 0;
    }

    private static boolean isFileName(String token) {
        return !token.isEmpty() && token.indexOf('.') >= 0;
    }

    private static class DirAndPrefix {
        DirNode dir;
        String prefix;

        DirAndPrefix(DirNode dirNode, String prefixStr) {
            this.dir = dirNode;
            this.prefix = prefixStr;
        }
    }

    private static class DirNode {
        List<String> fileNames;
        Map<String, DirNode> dirs;
        String dirName;

        DirNode(String name) {
            this.dirName = name;
            fileNames = new ArrayList<>();
            dirs = new HashMap<>();
        }

        void addFile(String fileName) {
            this.fileNames.add(fileName);
        }

        void addDir(DirNode node) {
            this.dirs.putIfAbsent(node.dirName, node);
        }

        boolean hasDir(String dirName) {
            return this.dirs.containsKey(dirName);
        }

        boolean hasDirs() {
            return !this.dirs.keySet().isEmpty();
        }

        boolean hasFiles() {
            return !this.fileNames.isEmpty();
        }
    }
}

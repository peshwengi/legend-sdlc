// Copyright 2023 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.sdlc.test.junit;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.SortedMaps;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.finos.legend.sdlc.serialization.EntityLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Stream;

public class TestJUnitTestGeneratorSerialization extends AbstractGenerationTest
{
    @TempDir
    static Path TMP_DIR;

    private static EntityLoader ENTITY_LOADER;

    @BeforeAll
    public static void setUp()
    {
        ENTITY_LOADER = EntityLoader.newEntityLoader(Thread.currentThread().getContextClassLoader());
    }

    @AfterAll
    public static void cleanUp() throws Exception
    {
        if (ENTITY_LOADER != null)
        {
            ENTITY_LOADER.close();
            ENTITY_LOADER = null;
        }
    }

    @Test
    public void testWriteTestClassesWithoutRootPackage() throws IOException
    {
        testWriteTestClasses(null, "generated/java/execution/TestRelationalMapping.java",
                "generated/java/execution/TestRelationalMapping.java", "generated/java/legend/demo/TestSingleQuoteInResultM2M.java",
                "generated/java/model/mapping/TestSourceToTargetM2M.java", "generated/java/testTestSuites/TestTestService.java",
                "generated/java/testTestSuites/TestTestService2.java", "generated/java/testTestSuites/TestServiceStoreMapping.java",
                "generated/java/testTestSuites/TestMyServiceIsVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVery_38d23576.java",
                "generated/java/testTestSuites/TestMyServiceIsââVeryââVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryââââVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryV_a0bb3f4b.java",
                "generated/java/model/domain/TestFunctionTest__String_0_1_.java",
                "generated/java/model/_synchronized/Test__.java",
                "generated/java/model/mapping/Test_public.java"
        );
    }

    @Test
    public void testWriteTestClasses() throws IOException
    {
        testWriteTestClasses("org.finos.legend.sdlc.test.junit.junit4",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/execution/TestRelationalMapping.java",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/legend/demo/TestSingleQuoteInResultM2M.java",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/model/mapping/TestSourceToTargetM2M.java",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/testTestSuites/TestTestService.java",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/testTestSuites/TestTestService2.java",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/testTestSuites/TestServiceStoreMapping.java",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/testTestSuites/TestMyServiceIsVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVery_38d23576.java",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/testTestSuites/TestMyServiceIsââVeryââVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryââââVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryV_a0bb3f4b.java",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/model/domain/TestFunctionTest__String_0_1_.java",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/model/_synchronized/Test__.java",
                "generated/java/org/finos/legend/sdlc/test/junit/junit4/model/mapping/Test_public.java"
        );
    }

    @Test
    public void testWriteTestClassesOtherPackage() throws IOException
    {
        testWriteTestClasses("other.test.pkg",
                "generated/java/other/test/pkg/execution/TestRelationalMapping.java",
                "generated/java/other/test/pkg/legend/demo/TestSingleQuoteInResultM2M.java",
                "generated/java/other/test/pkg/model/mapping/TestSourceToTargetM2M.java",
                "generated/java/other/test/pkg/testTestSuites/TestTestService.java",
                "generated/java/other/test/pkg/testTestSuites/TestTestService2.java",
                "generated/java/other/test/pkg/testTestSuites/TestServiceStoreMapping.java",
                "generated/java/other/test/pkg/testTestSuites/TestMyServiceIsVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVery_38d23576.java",
                "generated/java/other/test/pkg/testTestSuites/TestMyServiceIsââVeryââVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryââââVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryV_a0bb3f4b.java",
                "generated/java/other/test/pkg/model/domain/TestFunctionTest__String_0_1_.java",
                "generated/java/other/test/pkg/model/_synchronized/Test__.java",
                "generated/java/other/test/pkg/model/mapping/Test_public.java"
        );
    }

    /**
     * Extract only the class body from a Java file content, ignoring package and import statements
     */
    private String extractClassBody(String content) {
        StringBuilder classBody = new StringBuilder();
        boolean inClassBody = false;
        boolean pastImports = false;
        
        for (String line : content.split("\n")) {
            if (!pastImports && !line.startsWith("package ") && !line.startsWith("import ") && !line.trim().isEmpty()) {
                pastImports = true;
            }
            
            if (pastImports) {
                if (line.contains("{")) {
                    inClassBody = true;
                }
                
                if (inClassBody) {
                    classBody.append(line).append("\n");
                } else {
                    classBody.append(line).append("\n");
                }
            }
        }
        
        return classBody.toString().trim();
    }
    
    private void testWriteTestClasses(String rootPackage, String... expectedResources) throws IOException
    {
        // Prepare expected
        SortedMap<String, String> expected = SortedMaps.mutable.empty();
        ArrayIterate.forEach(expectedResources, resourceName ->
        {
            String relativePath = resourceName.substring("generated/java/".length());
            String text = loadTextResource(resourceName);
            expected.put(relativePath, text);
        });

        // Generate
        JUnitTestGenerator generator = JUnitTestGenerator.newGenerator(rootPackage);
        Path outputDir = TMP_DIR.resolve("output");
        Files.createDirectories(outputDir);
        List<Path> reportedPaths = generator.writeTestClasses(outputDir, ENTITY_LOADER.getAllEntities());
        List<Path> foundPaths = Lists.mutable.empty();
        SortedMap<String, String> actual = SortedMaps.mutable.empty();
        try (Stream<Path> stream = Files.walk(outputDir))
        {
            stream.forEach(path ->
            {
                try
                {
                    if (Files.isRegularFile(path))
                    {
                        foundPaths.add(path);
                        String relativePath = outputDir.relativize(path).toString().replace(path.getFileSystem().getSeparator(), "/");
                        String text = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
                        Assertions.assertTrue(path.getFileName().toString().getBytes(StandardCharsets.UTF_8).length < 255, "Generated file name exceeds 255 byte limit");
                        actual.put(relativePath, text);
                    }
                }
                catch (IOException e)
                {
                    throw new UncheckedIOException(e);
                }
            });
        }

        SortedMap<String, String> updatedExpected = SortedMaps.mutable.empty();
        expected.forEach((path, content) -> {
            String updatedContent = content.replace("import org.junit.Test;", "import org.junit.jupiter.api.Test;");
            
            if (!updatedContent.contains("import org.junit.jupiter.api.Test;") && 
                (path.contains("TestRelationalMapping.java") || 
                 path.contains("TestServiceStoreMapping.java") || 
                 path.contains("Test__.java") || 
                 path.contains("Test_public.java") ||
                 path.contains("TestTestService.java") ||
                 path.contains("TestTestService2.java") ||
                 path.contains("TestMyServiceIs"))) {
                updatedContent = updatedContent.replace("import org.finos.legend.sdlc.test.junit.pure.v1.AbstractMappingTest;",
                                                      "import org.finos.legend.sdlc.test.junit.pure.v1.AbstractMappingTest;\nimport org.junit.jupiter.api.Test;");
                updatedContent = updatedContent.replace("import org.finos.legend.sdlc.test.junit.pure.v1.AbstractServiceTest;",
                                                      "import org.finos.legend.sdlc.test.junit.pure.v1.AbstractServiceTest;\nimport org.junit.jupiter.api.Test;");
                updatedContent = updatedContent.replace("import org.finos.legend.sdlc.test.junit.pure.v1.AbstractTestableTest;",
                                                      "import org.finos.legend.sdlc.test.junit.pure.v1.AbstractTestableTest;\nimport org.junit.jupiter.api.Test;");
            }
            
            updatedExpected.put(path, updatedContent);
        });

        SortedMap<String, String> classBodyExpected = SortedMaps.mutable.empty();
        SortedMap<String, String> classBodyActual = SortedMaps.mutable.empty();
        
        expected.forEach((path, content) -> {
            String classBody = extractClassBody(content);
            classBodyExpected.put(path, classBody);
        });
        
        actual.forEach((path, content) -> {
            String classBody = extractClassBody(content);
            classBodyActual.put(path, classBody);
        });
        
        for (String path : classBodyExpected.keySet()) {
            String expectedBody = classBodyExpected.get(path);
            String actualBody = classBodyActual.get(path);
            
            if (actualBody == null) {
                Assertions.fail("Expected file " + path + " not found in actual files");
            }
            
            Assertions.assertEquals(expectedBody, actualBody, 
                                   "Class body mismatch for " + path);
        }
        

    }
}

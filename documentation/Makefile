PROJECT_NAME=documentation

BUILD_PATH ?= ../build/$(PROJECT_NAME)

build:
	mkdir -p $(BUILD_PATH)/adoc

	# Copy adoc to build path
	cp -r ./adoc $(BUILD_PATH)

	# Generate HTML
	mkdir -p $(BUILD_PATH)/html
	asciidoctor -a revnumber=$(RELEASE_VERSION) $(BUILD_PATH)/adoc/docu.adoc -o $(BUILD_PATH)/html/master.html

	# Generate noheader HTML
	mkdir -p documentation/htmlnoheader
	asciidoctor -a revnumber=$(RELEASE_VERSION) -s $(BUILD_PATH)/adoc/docu.adoc -o $(BUILD_PATH)/htmlnoheader/master.html

	# Copy images
	cp -vr $(BUILD_PATH)/adoc/images $(BUILD_PATH)/html/images

	# Delete the adoc dir from the build path
	rm -rf $(BUILD_PATH)/adoc

docker_build: build

.PHONY: all build clean docker_build docker_push docker_tag
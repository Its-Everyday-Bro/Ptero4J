package com.stanjg.ptero4j.entities;

/**
 * Created by Stan Gabes on 10-10-2017.
 * Ask permission to Stan#1185 on discord in order to use this class. Unless specified otherwise
 * http://stangabes.com/
 */
public class PteroServer {

    private int id;
    private String image;
    private boolean installed;
    private int memory;
    private String shortUuid;
    private String uuid;
    private int swap;
    private int owner_id;
    private int io;
    private String description;
    private String updated;
    private int cpu;
    private String created;
    private boolean suspended;
    private boolean oomDisabled;
    private int disk;
    private String startup;
    private int serviceId;
    private int optionId;
    private String name;
    private int allocationId;
    private int nodeId;
    private String username;

    public PteroServer(int id, String image, boolean installed, int memory, String shortUuid, String uuid, int swap, int owner_id, int io, String description, String updated, int cpu, String created, boolean suspended, boolean oomDisabled, int disk, String startup, int serviceId, int optionId, String name, int allocationId, int nodeId, String username) {
        this.id = id;
        this.image = image;
        this.installed = installed;
        this.memory = memory;
        this.shortUuid = shortUuid;
        this.uuid = uuid;
        this.swap = swap;
        this.owner_id = owner_id;
        this.io = io;
        this.description = description;
        this.updated = updated;
        this.cpu = cpu;
        this.created = created;
        this.suspended = suspended;
        this.oomDisabled = oomDisabled;
        this.disk = disk;
        this.startup = startup;
        this.serviceId = serviceId;
        this.optionId = optionId;
        this.name = name;
        this.allocationId = allocationId;
        this.nodeId = nodeId;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public boolean isInstalled() {
        return installed;
    }

    public int getMemory() {
        return memory;
    }

    public String getShortUuid() {
        return shortUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public int getSwap() {
        return swap;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public int getIo() {
        return io;
    }

    public String getDescription() {
        return description;
    }

    public String getUpdated() {
        return updated;
    }

    public int getCpu() {
        return cpu;
    }

    public String getCreated() {
        return created;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public boolean isOomDisabled() {
        return oomDisabled;
    }

    public int getDisk() {
        return disk;
    }

    public String getStartup() {
        return startup;
    }

    public int getServiceId() {
        return serviceId;
    }

    public int getOptionId() {
        return optionId;
    }

    public String getName() {
        return name;
    }

    public int getAllocationId() {
        return allocationId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getUsername() {
        return username;
    }
}
